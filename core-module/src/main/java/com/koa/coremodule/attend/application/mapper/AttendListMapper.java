package com.koa.coremodule.attend.application.mapper;

import com.koa.coremodule.attend.application.dto.AttendList;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AttendListMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("a hh:mm", Locale.KOREA);

    public static AttendList toResponse(AttendListProjection projection) {
        AttendList response = AttendList.builder()
                .attendId(projection.getAttendId())
                .curriculum(projection.getCurriculumName())
                .date(projection.getAttendTime().format(DATE_FORMATTER))
                .time(projection.getAttendTime().format(TIME_FORMATTER))
                .status(projection.getStatus())
                .build();
        return response;
    }
}
