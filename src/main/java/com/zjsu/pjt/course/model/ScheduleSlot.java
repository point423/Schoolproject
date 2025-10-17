package com.zjsu.pjt.course.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程时间安排实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSlot {
    // 星期（枚举值：MONDAY/TUESDAY/WEDNESDAY/THURSDAY/FRIDAY/SATURDAY/SUNDAY）
    @NotBlank(message = "星期不能为空")
    private String dayOfWeek;

    // 开始时间（格式：HH:MM，如08:00）
    @NotBlank(message = "开始时间不能为空")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "开始时间格式错误（需为HH:MM）")//匹配 "00-19",匹配 "20-23" ,00-59
    private String startTime;

    // 结束时间（格式：HH:MM）
    @NotBlank(message = "结束时间不能为空")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "结束时间格式错误（需为HH:MM）")
    private String endTime;

    // 预计出勤人数
    private Integer expectedAttendance;
}