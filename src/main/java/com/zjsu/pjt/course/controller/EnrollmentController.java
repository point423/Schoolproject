package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Enrollment;
import com.zjsu.pjt.course.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // 1. 学生选课 POST /api/enrollments
    @PostMapping
    @Operation(summary = "学生选课", description = "学生选择课程，需校验课程容量和重复选课，HTTP请求方式为POST")
    public ResponseEntity<Response<Enrollment>> enrollCourse(@Valid @RequestBody Enrollment enrollment) {
        Enrollment enrolled = enrollmentService.enrollCourse(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Enrolled successfully", enrolled));
    }

    // 2. 学生退课 DELETE /api/enrollments/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "学生退课", description = "根据选课记录ID删除选课信息，HTTP请求方式为DELETE")
    public ResponseEntity<Response<Void>> dropCourse(@PathVariable String id) {
        enrollmentService.dropCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }

    // 3. 查询所有选课记录 GET /api/enrollments
    @GetMapping
    @Operation(summary = "查询所有选课记录", description = "获取系统中所有的选课记录列表，HTTP请求方式为GET")
    public ResponseEntity<Response<List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(Response.success(enrollments));
    }

    // 4. 按课程查询选课记录 GET /api/enrollments/course/{courseId}
    @GetMapping("/course/{courseId}")
    @Operation(summary = "按课程查询选课记录", description = "根据课程ID查询该课程的所有选课记录，HTTP请求方式为GET")
    public ResponseEntity<Response<List<Enrollment>>> getEnrollmentsByCourseId(@PathVariable String courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(Response.success(enrollments));
    }

    // 5. 按学生查询选课记录 GET /api/enrollments/student/{studentId}
    @GetMapping("/student/{studentId}")
    @Operation(summary = "按学生查询选课记录", description = "根据学生ID查询该学生的所有选课记录，HTTP请求方式为GET")
    public ResponseEntity<Response<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable String studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(Response.success(enrollments));
    }
}