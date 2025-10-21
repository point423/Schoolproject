package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Student;
import com.zjsu.pjt.course.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理API（RESTful）
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "学生管理模块", description = "提供学生的创建、查询、更新、删除等操作，含学号唯一校验、邮箱格式校验")
public class StudentController {
    private final StudentService studentService;

    // 1. 创建学生 POST /api/students
    @PostMapping
    @Operation(summary = "创建学生", description = "新增学生信息，系统自动生成学生ID和创建时间，HTTP请求方式为POST")
    public ResponseEntity<Response<Student>> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Student created successfully", createdStudent));
    }

    // 2. 查询所有学生 GET /api/students
    @GetMapping
    @Operation(summary = "查询所有学生", description = "获取系统中所有学生的列表，HTTP请求方式为GET")
    public ResponseEntity<Response<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(Response.success(students));
    }

    // 3. 根据ID查询学生 GET /api/students/{id}
    @GetMapping("/{id}")
    @Operation(summary = "查询单个学生", description = "根据学生ID查询学生详情，学生不存在时返回404，HTTP请求方式为GET")
    public ResponseEntity<Response<Student>> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(Response.success(student));
    }

    // 4. 更新学生 PUT /api/students/{id}
    @PutMapping("/{id}")
    @Operation(summary = "更新学生", description = "根据学生ID全量更新学生信息，更新学号时需校验唯一性，HTTP请求方式为PUT")
    public ResponseEntity<Response<Student>> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody Student student
    ) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(Response.success(updatedStudent));
    }

    // 5. 删除学生 DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "删除学生", description = "根据学生ID删除学生信息，存在选课记录时禁止删除，HTTP请求方式为DELETE")
    public ResponseEntity<Response<Void>> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }
}