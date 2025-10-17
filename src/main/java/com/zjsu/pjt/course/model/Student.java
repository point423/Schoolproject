package com.zjsu.pjt.course.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学生实体
 */
@Data//自动生成所有字段的getter和setter方法
@NoArgsConstructor//自动生成无参构造方法
@AllArgsConstructor//自动生成全参构造方法
public class Student {
    private String id; // 系统生成UUID

    @NotBlank(message = "学号不能为空")//数据校验,不为 null.校验字符串
    private String studentId; // 学号（全局唯一）

    @NotBlank(message = "学生姓名不能为空")
    private String name; // 学生姓名

    @NotBlank(message = "专业不能为空")
    private String major; // 专业（如计算机科学与技术）

    @Min(value = 2000, message = "入学年份不能小于2000")//校验Integer、Long.其值大于等于value
    private Integer grade; // 入学年份（如2024）

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误（需包含@和域名）")//校验字符串是否符合邮箱格式
    private String email; // 邮箱

    private LocalDateTime createdAt; // 系统生成创建时间
}