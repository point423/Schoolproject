package com.zjsu.pjt.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "选课记录实体类，记录学生与课程的选课关系")
public class Enrollment {
    @Schema(description = "选课记录唯一ID（系统自动生成UUID）", example = "123e4567-e89b-12d3-a456-426614174000", required = false, readOnly = true)
    private String id; // 系统生成UUID

    @Schema(description = "关联的课程ID（对应课程表的ID）", example = "123e4567-e89b-12d3-a456-426614174001", required = true)
    @NotBlank(message = "课程ID不能为空")
    private String courseId; // 关联的课程ID

    @Schema(description = "关联的学生ID（对应学生表的ID，系统生成的UUID）", example = "123e4567-e89b-12d3-a456-426614174002", required = true)
    @NotBlank(message = "学生ID不能为空")
    private String studentId; // 关联的学生ID（系统生成的UUID，非学号）

    @Schema(description = "选课时间（系统自动生成）", example = "2024-10-01T14:30:00", required = false, readOnly = true)
    private LocalDateTime enrolledAt; // 选课时间（系统生成）
}