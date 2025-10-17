package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Enrollment;
import com.zjsu.pjt.course.service.EnrollmentService;
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
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    // 1. 学生选课 POST /api/enrollments
    @PostMapping
    public ResponseEntity<Response<Enrollment>> enrollCourse(@Valid @RequestBody Enrollment enrollment) {
        Enrollment enrolled = enrollmentService.enrollCourse(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Enrolled successfully", enrolled));
    }

    // 2. 学生退课 DELETE /api/enrollments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> dropCourse(@PathVariable String id) {
        enrollmentService.dropCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }

    // 3. 查询所有选课记录 GET /api/enrollments
    @GetMapping
    public ResponseEntity<Response<List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(Response.success(enrollments));
    }

    // 4. 按课程查询选课记录 GET /api/enrollments/course/{courseId}
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Response<List<Enrollment>>> getEnrollmentsByCourseId(@PathVariable String courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(Response.success(enrollments));
    }

    // 5. 按学生查询选课记录 GET /api/enrollments/student/{studentId}
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Response<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable String studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(Response.success(enrollments));
    }
}