package com.zjsu.pjt.course.repository;

import com.zjsu.pjt.course.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 课程数据访问（内存存储）
 */
@Repository
public class CourseRepository {
    // 内存存储：key=课程ID，value=课程对象
    private final Map<String, Course> courses = new ConcurrentHashMap<>();

    // 查询所有课程
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    // 根据ID查询课程
    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    // 保存课程（新增）
    public Course save(Course course) {
        // 生成UUID作为课程ID
        String courseId = UUID.randomUUID().toString();
        course.setId(courseId);
        // 默认已选人数为0
        if (course.getEnrolled() == null) {
            course.setEnrolled(0);
        }
        courses.put(courseId, course);
        return course;
    }

    // 更新课程（全量更新）
    public Course update(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    // 删除课程
    public void deleteById(String id) {
        courses.remove(id);
    }

    // 检查课程编码是否唯一（防止重复编码）
    public boolean existsByCode(String code) {
        return courses.values().stream()
                .anyMatch(course -> code.equals(course.getCode()));
    }
}