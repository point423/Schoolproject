package com.zjsu.pjt.course.controller;

import com.zjsu.pjt.course.common.Response;
import com.zjsu.pjt.course.model.Student;
import com.zjsu.pjt.course.service.StudentService;
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
public class StudentController {
    private final StudentService studentService;

    // 1. 创建学生 POST /api/students
    @PostMapping
    public ResponseEntity<Response<Student>> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Student created successfully", createdStudent));
    }

    // 2. 查询所有学生 GET /api/students
    @GetMapping
    public ResponseEntity<Response<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(Response.success(students));
    }

    // 3. 根据ID查询学生 GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Response<Student>> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(Response.success(student));
    }

    // 4. 更新学生 PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Response<Student>> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody Student student
    ) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(Response.success(updatedStudent));
    }

    // 5. 删除学生 DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success());
    }
}