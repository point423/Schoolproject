package com.zjsu.pjt.course.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 选课记录实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private String id; // 系统生成UUID

    @NotBlank(message = "课程ID不能为空")
    private String courseId; // 关联的课程ID

    @NotBlank(message = "学生ID不能为空")
    private String studentId; // 关联的学生ID（系统生成的UUID，非学号）

    private LocalDateTime enrolledAt; // 选课时间（系统生成）
}