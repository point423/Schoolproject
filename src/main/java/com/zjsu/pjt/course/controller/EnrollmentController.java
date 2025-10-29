package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.BusinessException;
import com.zjsu.pjt.course.model.Enrollment;
import com.zjsu.pjt.course.service.EnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选课管理API（RESTful）
 */
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "选课管理模块", description = "提供学生选课、退课及选课记录查询等操作")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    // 学生选课
    @PostMapping
    public ResponseEntity<Enrollment> enrollCourse(@RequestBody Enrollment enrollment) {
        return ResponseEntity.status(201).body(enrollmentService.enrollCourse(enrollment));
    }

    // 学生退课（按课程ID和学生ID）
    @DeleteMapping
    public ResponseEntity<Void> dropCourse(
            @RequestParam String courseId,
            @RequestParam String studentId
    ) {
        enrollmentService.dropCourse(courseId, studentId);
        return ResponseEntity.noContent().build();
    }

    // 按课程ID查询活跃选课记录
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getActiveEnrollmentsByCourseId(@PathVariable String courseId) {
        return ResponseEntity.ok(enrollmentService.getActiveEnrollmentsByCourseId(courseId));
    }

    // 统计课程活跃人数
    @GetMapping("/course/{courseId}/count")
    public ResponseEntity<Integer> countActiveEnrollments(@PathVariable String courseId) {
        return ResponseEntity.ok(enrollmentService.countActiveEnrollments(courseId));
    }

    // 全局异常处理
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
