package com.koa.coremodule.curriculum.application.dto.request;


import java.time.LocalDateTime;

public record CurriculumCreateRequest(
        String curriculumName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String place,
        String wifiName,
        String wifiPassword
) {
}
