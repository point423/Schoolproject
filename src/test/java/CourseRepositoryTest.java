import com.zjsu.pjt.course.CourseApplication;  // 导入主启动类
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.model.Instructor;
import com.zjsu.pjt.course.model.Schedule;
import com.zjsu.pjt.course.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * CourseRepository 集成测试（使用H2内存库）
 */
@DataJpaTest
@ContextConfiguration(classes = CourseApplication.class) // 核心配置：指定主启动类
@ActiveProfiles("dev")  // 激活开发环境配置
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testSaveAndFindByCode() {
        // 1. 构造测试课程
        Course course = new Course();
        course.setCode("TEST-001");
        course.setTitle("测试课程");
        course.setInstructor(new Instructor("测试讲师", "test@school.com"));
        course.setSchedule(new Schedule("MONDAY", "09:00", "10:30"));
        course.setCapacity(30);
        course.setEnrolled(5);

        // 2. 保存课程
        Course savedCourse = courseRepository.save(course);
        Assertions.assertNotNull(savedCourse.getId());

        // 3. 按课程代码查询
        Course foundCourse = courseRepository.findByCode("TEST-001").orElse(null);
        Assertions.assertNotNull(foundCourse);
        Assertions.assertEquals("测试课程", foundCourse.getTitle());
        Assertions.assertEquals(30, foundCourse.getCapacity());
    }

    @Test
    public void testExistsByCode() {
        // 1. 保存测试课程
        Course course = new Course();
        course.setCode("TEST-002");
        course.setTitle("测试课程2");
        course.setInstructor(new Instructor("测试讲师2", "test2@school.com"));
        course.setSchedule(new Schedule("TUESDAY", "14:00", "15:30"));
        course.setCapacity(20);
        courseRepository.save(course);

        // 2. 校验课程代码存在
        Assertions.assertTrue(courseRepository.existsByCode("TEST-002"));
        // 校验课程代码不存在
        Assertions.assertFalse(courseRepository.existsByCode("TEST-999"));
    }
}