package com.zjsu.pjt.course.service;

import com.zjsu.pjt.course.common.BusinessException;
import com.zjsu.pjt.course.common.ResourceNotFoundException;
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.model.Enrollment;
import com.zjsu.pjt.course.model.Student;
import com.zjsu.pjt.course.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选课业务逻辑
 */
@Service
@RequiredArgsConstructor
public class EnrollmentService {
    // 注入三个仓库和课程、学生服务（业务校验用）
    private final EnrollmentRepository enrollmentRepository;
    private final CourseService courseService;
    private final StudentService studentService;

    // 查询所有选课记录
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // 根据ID查询选课记录
    public Enrollment getEnrollmentById(String id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", id));
    }

    // 根据课程ID查询选课记录
    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        // 校验课程是否存在
        courseService.getCourseById(courseId);
        return enrollmentRepository.findByCourseId(courseId);
    }

    // 根据学生ID（UUID）查询选课记录
    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        // 校验学生是否存在
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    // 学生选课（核心业务）
    public Enrollment enrollCourse(Enrollment enrollment) {
        String courseId = enrollment.getCourseId();
        String studentId = enrollment.getStudentId(); // 学生的UUID（非学号）

        // 1. 校验课程是否存在
        Course course = courseService.getCourseById(courseId);

        // 2. 校验学生是否存在
        Student student = studentService.getStudentById(studentId);

        // 3. 校验是否重复选课
        if (enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new BusinessException("学生" + student.getStudentId() + "已选该课程：" + course.getTitle());
        }

        // 4. 校验课程容量
        if (course.getEnrolled() >= course.getCapacity()) {
            throw new BusinessException("课程" + course.getTitle() + "容量已满（当前：" + course.getEnrolled() + "/" + course.getCapacity() + "）");
        }

        // 5. 保存选课记录
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // 6. 更新课程已选人数（自增）
        courseService.incrementEnrolled(courseId);

        return savedEnrollment;
    }

    // 学生退课
    public void dropCourse(String id) {
        // 1. 校验选课记录是否存在
        Enrollment enrollment = getEnrollmentById(id);
        String courseId = enrollment.getCourseId();

        // 2. 删除选课记录
        enrollmentRepository.deleteById(id);

        // 3. 更新课程已选人数（自减）
        courseService.decrementEnrolled(courseId);
    }

    // 根据课程ID和学生ID退课（可选，用于特殊场景）
    public void dropCourseByCourseIdAndStudentId(String courseId, String studentId) {
        // 校验选课记录是否存在
        if (!enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new BusinessException("学生未选该课程");
        }
        // 删除选课记录
        enrollmentRepository.deleteByCourseIdAndStudentId(courseId, studentId);
        // 更新课程已选人数
        courseService.decrementEnrolled(courseId);
    }
}