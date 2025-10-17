package com.zjsu.pjt.course;

import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.model.Instructor;
import com.zjsu.pjt.course.model.ScheduleSlot;
import com.zjsu.pjt.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 项目启动类
 */
@SpringBootApplication
@RequiredArgsConstructor
public class CourseApplication {
    private final CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    // 初始化测试数据（可选，启动后自动创建3门测试课程）
    @Bean
    CommandLineRunner initTestData() {
        return args -> {
            // 测试课程1
            Course course1 = new Course();
            course1.setCode("CS101");
            course1.setTitle("计算机科学导论");
            course1.setInstructor(new Instructor("T001", "张教授", "zhang@example.edu.cn"));
            course1.setSchedule(new ScheduleSlot("MONDAY", "08:00", "10:00", 50));
            course1.setCapacity(60);
            courseService.createCourse(course1);

            // 测试课程2
            Course course2 = new Course();
            course2.setCode("MA101");
            course2.setTitle("高等数学");
            course2.setInstructor(new Instructor("T002", "李教授", "li@example.edu.cn"));
            course2.setSchedule(new ScheduleSlot("TUESDAY", "10:30", "12:30", 40));
            course2.setCapacity(50);
            courseService.createCourse(course2);

            // 测试课程3
            Course course3 = new Course();
            course3.setCode("EN101");
            course3.setTitle("大学英语");
            course3.setInstructor(new Instructor("T003", "王老师", "wang@example.edu.cn"));
            course3.setSchedule(new ScheduleSlot("WEDNESDAY", "14:00", "16:00", 30));
            course3.setCapacity(40);
            courseService.createCourse(course3);

            System.out.println("✅ 测试课程初始化完成！");
        };
    }
}