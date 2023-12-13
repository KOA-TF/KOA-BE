package com.koa.coremodule.attend.application.mapper;

import com.koa.coremodule.attend.application.dto.AttendList;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;

public final class AttendListMapper {

    private AttendListMapper() {
    }

    public static AttendList toResponse(AttendListProjection projection) {
        return AttendList.builder()
                .attendId(projection.getAttendId())
                .curriculum(projection.getCurriculumName())
                .date(projection.getDate())
                .status(projection.getStatus())
                .time(projection.getAttendTime())
                .build();
    }

}
