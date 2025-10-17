package com.zjsu.pjt.course.repository;

import com.zjsu.pjt.course.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 学生数据访问（内存存储）
 */
@Repository
public class StudentRepository {
    // 用内存存储：key=（UUID），value=学生对象。程序重启后map数据清空
    private final Map<String, Student> students = new ConcurrentHashMap<>();

    // 查询所有学生
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    // 根据ID查询学生。如果值不存在，optional是空的（但不是 null）. 当值可能为 null 时使用 ofNullable
    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }

    // 根据学号查询学生（校验唯一性），用stream流遍历
    public Optional<Student> findByStudentId(String studentId) {
        return students.values().stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst();
    }

    // 保存学生（新增）
    public Student save(Student student) {
        // 生成UUID作为学生ID
        String studentId = UUID.randomUUID().toString();
        student.setId(studentId);
        // 生成创建时间（当前时间）
        student.setCreatedAt(java.time.LocalDateTime.now());
        students.put(studentId, student);
        return student;
    }

    // 更新学生（全量更新）
    public Student update(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    // 删除学生
    public void deleteById(String id) {
        students.remove(id);
    }

    // 检查学号是否已存在.ispresent判断值是否存在
    public boolean existsByStudentId(String studentId) {
        return findByStudentId(studentId).isPresent();
    }
}