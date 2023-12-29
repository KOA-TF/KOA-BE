package com.koa.coremodule.curriculum.application.dto.response;

public record RecentCurriculumResponse(
        String curriculumName,
        String time,
        String place,
        String wifiName,
        String wifiPassword
){
}
