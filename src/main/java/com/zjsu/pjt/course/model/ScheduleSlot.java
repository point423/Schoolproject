package com.zjsu.pjt.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "课程时间安排实体类，包含上课时间、星期等信息")
public class ScheduleSlot {
    @Schema(description = "星期（枚举值：MONDAY/TUESDAY/WEDNESDAY/THURSDAY/FRIDAY/SATURDAY/SUNDAY）", example = "MONDAY", required = true)
    @NotBlank(message = "星期不能为空")
    private String dayOfWeek;

    @Schema(description = "开始时间（格式：HH:MM，如08:00）", example = "08:00", required = true)
    @NotBlank(message = "开始时间不能为空")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "开始时间格式错误（需为HH:MM）")
    private String startTime;

    @Schema(description = "结束时间（格式：HH:MM）", example = "10:00", required = true)
    @NotBlank(message = "结束时间不能为空")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "结束时间格式错误（需为HH:MM）")
    private String endTime;

    @Schema(description = "预计出勤人数", example = "50", required = false)
    private Integer expectedAttendance;
}