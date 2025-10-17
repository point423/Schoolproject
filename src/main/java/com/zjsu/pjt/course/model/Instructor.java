package com.zjsu.pjt.course.model;

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
public class Instructor {
    @NotBlank(message = "教师ID不能为空")
    private String id; // 教师工号（如T001）

    @NotBlank(message = "教师姓名不能为空")
    private String name; // 教师姓名

    @NotBlank(message = "教师邮箱不能为空")
    private String email; // 教师邮箱
}