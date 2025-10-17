package com.zjsu.pjt.course.repository;

import com.zjsu.pjt.course.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 选课记录数据访问（内存存储）
 */
@Repository
public class EnrollmentRepository {
    // 内存存储：key=选课ID，value=选课记录
    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    // 查询所有选课记录
    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments.values());
    }

    // 根据ID查询选课记录
    public Optional<Enrollment> findById(String id) {
        return Optional.ofNullable(enrollments.get(id));
    }

    // 根据课程ID查询选课记录
    public List<Enrollment> findByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(enrollment -> courseId.equals(enrollment.getCourseId()))
                .collect(Collectors.toList());
    }

    // 根据学生ID（UUID）查询选课记录
    public List<Enrollment> findByStudentId(String studentId) {
        return enrollments.values().stream()
                .filter(enrollment -> studentId.equals(enrollment.getStudentId()))
                .collect(Collectors.toList());
    }

    // 检查学生是否已选该课程（防重复选课）
    public boolean existsByCourseIdAndStudentId(String courseId, String studentId) {
        return enrollments.values().stream()
                .anyMatch(enrollment ->
                        courseId.equals(enrollment.getCourseId())
                                && studentId.equals(enrollment.getStudentId())
                );
    }

    // 保存选课记录（新增）
    public Enrollment save(Enrollment enrollment) {
        // 生成UUID作为选课ID
        String enrollmentId = UUID.randomUUID().toString();
        enrollment.setId(enrollmentId);
        // 生成选课时间（当前时间）
        enrollment.setEnrolledAt(java.time.LocalDateTime.now());
        enrollments.put(enrollmentId, enrollment);
        return enrollment;
    }

    // 删除选课记录
    public void deleteById(String id) {
        enrollments.remove(id);
    }

    // 根据课程ID和学生ID删除选课记录（退课时用）
    public void deleteByCourseIdAndStudentId(String courseId, String studentId) {
        enrollments.values().removeIf(enrollment ->
                courseId.equals(enrollment.getCourseId())
                        && studentId.equals(enrollment.getStudentId())
        );
    }
}