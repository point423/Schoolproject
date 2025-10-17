package com.zjsu.pjt.course.service;

import com.zjsu.pjt.course.common.BusinessException;
import com.zjsu.pjt.course.common.ResourceNotFoundException;
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程业务逻辑
 */
@Service
@RequiredArgsConstructor
public class CourseService {
    // 注入课程仓库
    private final CourseRepository courseRepository;

    // 查询所有课程
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // 根据ID查询课程
    public Course getCourseById(String id) {
        // 若不存在，抛资源不存在异常
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    // 创建课程
    public Course createCourse(Course course) {
        // 校验课程编码唯一性（可选，防止重复）
        if (courseRepository.existsByCode(course.getCode())) {
            throw new BusinessException("课程编码已存在：" + course.getCode());
        }
        return courseRepository.save(course);
    }

    // 更新课程
    public Course updateCourse(String id, Course course) {
        // 先查课程是否存在
        getCourseById(id); // 不存在会抛异常
        // 设置课程ID（确保更新的是指定ID的课程）
        course.setId(id);
        return courseRepository.update(course);
    }

    // 删除课程
    public void deleteCourse(String id) {
        // 先查课程是否存在
        getCourseById(id);
        courseRepository.deleteById(id);
    }

    // 选课时更新课程已选人数（自增）
    public void incrementEnrolled(String courseId) {
        Course course = getCourseById(courseId);
        // 校验容量（防止超容，实际业务在选课Service中已校验，此处双重保险）
        if (course.getEnrolled() >= course.getCapacity()) {
            throw new BusinessException("课程容量已满：" + course.getTitle());
        }
        course.setEnrolled(course.getEnrolled() + 1);
        courseRepository.update(course);
    }

    // 退课时更新课程已选人数（自减）
    public void decrementEnrolled(String courseId) {
        Course course = getCourseById(courseId);
        // 已选人数不能小于0
        if (course.getEnrolled() <= 0) {
            course.setEnrolled(0);
            courseRepository.update(course);
            return;
        }
        course.setEnrolled(course.getEnrolled() - 1);
        courseRepository.update(course);
    }
}