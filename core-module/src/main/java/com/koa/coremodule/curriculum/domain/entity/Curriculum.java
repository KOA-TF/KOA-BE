package com.koa.coremodule.curriculum.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@ToString
public class Curriculum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    private String curriculumName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String place;
    private String wifiName;
    private String wifiPassword;

    public String getCurriculumTimeFormat() {
        return formatCurriculumTime();
    }

    private String formatCurriculumTime() {
        int month = startTime.getMonthValue();
        int day = startTime.getDayOfMonth();
        String dayOfWeek = startTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String startTimeStr = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String endTimeStr = (endTime != null) ? "~" + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
        return String.format("%d월 %d일 %s %s%s", month, day, dayOfWeek, startTimeStr, endTimeStr);
    }
}
