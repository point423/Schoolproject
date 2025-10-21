package com.zjsu.pjt.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师实体（课程关联的教师信息）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "教师信息实体类，关联课程的授课教师")
public class Instructor {
    @Schema(description = "教师工号", example = "T001", required = true)
    @NotBlank(message = "教师ID不能为空")
    private String id; // 教师工号（如T001）

    @Schema(description = "教师姓名", example = "张教授", required = true)
    @NotBlank(message = "教师姓名不能为空")
    private String name; // 教师姓名

    @Schema(description = "教师邮箱", example = "zhang@example.edu.cn", required = true)
    @NotBlank(message = "教师邮箱不能为空")
    private String email; // 教师邮箱
}