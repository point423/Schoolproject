package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "课程管理模块", description = "提供课程的创建、查询、更新、删除等操作")
public class CourseController {
    private final CourseService courseService;

    // 1. 查询所有课程 GET /api/courses
    @GetMapping
    @Operation(summary = "查询所有课程", description = "获取系统中所有课程的列表，HTTP请求方式为GET")
    public ResponseEntity<Response<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(Response.success(courses));
    }

    // 2. 查询单个课程 GET /api/courses/{id}
    @GetMapping("/{id}")
    @Operation(summary = "查询单个课程", description = "根据课程ID查询课程详情，HTTP请求方式为GET")
    public ResponseEntity<Response<Course>> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(Response.success(course));
    }

    // 3. 创建课程 POST /api/courses
    @PostMapping
    @Operation(summary = "创建课程", description = "新增课程信息，系统自动生成课程ID，HTTP请求方式为POST")
    public ResponseEntity<Response<Course>> createCourse(@Valid @RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Course created successfully", createdCourse));
    }

    // 4. 更新课程 PUT /api/courses/{id}
    @PutMapping("/{id}")
    @Operation(summary = "更新课程", description = "根据课程ID全量更新课程信息，HTTP请求方式为PUT")
    public ResponseEntity<Response<Course>> updateCourse(
            @PathVariable String id,
            @Valid @RequestBody Course course
    ) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(Response.success(updatedCourse));
    }

    // 5. 删除课程 DELETE /api/courses/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "删除课程", description = "根据课程ID删除课程信息，HTTP请求方式为DELETE")
    public ResponseEntity<Response<Void>> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }
}