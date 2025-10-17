package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程管理API（RESTful）
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // 1. 查询所有课程 GET /api/courses
    @GetMapping
    public ResponseEntity<Response<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(Response.success(courses));
    }

    // 2. 查询单个课程 GET /api/courses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Response<Course>> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(Response.success(course));
    }

    // 3. 创建课程 POST /api/courses
    @PostMapping
    public ResponseEntity<Response<Course>> createCourse(@Valid @RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        // 创建成功返回201状态码
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Course created successfully", createdCourse));
    }

    // 4. 更新课程 PUT /api/courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Response<Course>> updateCourse(
            @PathVariable String id,
            @Valid @RequestBody Course course
    ) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(Response.success(updatedCourse));
    }

    // 5. 删除课程 DELETE /api/courses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        // 删除成功返回204（无内容）或200
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }
}