package com.zjsu.pjt.course.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String id; // 系统生成UUID，作为主键

    @NotBlank(message = "课程编码不能为空")
    private String code; // 课程编码（如CS101）

    @NotBlank(message = "课程名称不能为空")
    private String title; // 课程名称

    @Valid // 嵌套校验（校验Instructor的字段）
    private Instructor instructor; // 授课教师

    @Valid // 嵌套校验（校验ScheduleSlot的字段）
    private ScheduleSlot schedule; // 课程时间安排

    @Min(value = 1, message = "课程容量不能小于1")
    private Integer capacity; // 课程容量（最大选课人数）

    private Integer enrolled = 0; // 已选人数（默认0，选课时自增）
}