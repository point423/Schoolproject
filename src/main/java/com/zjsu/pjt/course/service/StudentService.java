package com.zjsu.pjt.course.service;

import com.zjsu.pjt.course.common.BusinessException;
import com.zjsu.pjt.course.common.ResourceNotFoundException;
import com.zjsu.pjt.course.model.Student;
import com.zjsu.pjt.course.repository.EnrollmentRepository;
import com.zjsu.pjt.course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生业务逻辑
 */
@Service
@RequiredArgsConstructor
public class StudentService {
    // 注入学生仓库和选课仓库（删除学生时需检查选课记录）
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    // 查询所有学生
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 根据ID查询学生
    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));
    }

    // 根据学号查询学生（内部用，如选课校验）
    public Student getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student (by studentId)", studentId));
    }

    // 创建学生
    public Student createStudent(Student student) {
        // 校验学号唯一性
        if (studentRepository.existsByStudentId(student.getStudentId())) {
            throw new BusinessException("学号已存在：" + student.getStudentId());
        }
        // 校验邮箱格式（已通过@Email注解校验，此处可选）
        if (!student.getEmail().contains("@")) {
            throw new BusinessException("邮箱格式错误：" + student.getEmail());
        }
        return studentRepository.save(student);
    }

    // 更新学生
    public Student updateStudent(String id, Student student) {
        // 先查学生是否存在
        Student existingStudent = getStudentById(id);

        // 若更新学号，需校验新学号唯一性
        if (!existingStudent.getStudentId().equals(student.getStudentId())) {
            if (studentRepository.existsByStudentId(student.getStudentId())) {
                throw new BusinessException("更新的学号已存在：" + student.getStudentId());
            }
        }

        // 不可修改的字段（系统生成）
        student.setId(id);
        student.setCreatedAt(existingStudent.getCreatedAt());

        return studentRepository.update(student);
    }

    // 删除学生（需检查是否有选课记录）
    public void deleteStudent(String id) {
        // 先查学生是否存在
        getStudentById(id);

        // 检查是否有选课记录
        List<?> enrollments = enrollmentRepository.findByStudentId(id);
        if (!enrollments.isEmpty()) {
            throw new BusinessException("无法删除：该学生存在选课记录");
        }

        // 无选课记录，删除学生
        studentRepository.deleteById(id);
    }
}