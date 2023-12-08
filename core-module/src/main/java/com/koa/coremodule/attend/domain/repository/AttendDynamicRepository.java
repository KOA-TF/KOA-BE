package com.koa.coremodule.attend.domain.repository;

import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;

import java.util.List;

public interface AttendDynamicRepository {

    List<AttendListProjection> getAttendList(Long memberId);

}
