package com.koa.coremodule.attend.domain.repository.projection;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AttendStatusProjection {

    private AttendStatus status;
    private Long count;

    @QueryProjection
    public AttendStatusProjection(AttendStatus status, Long count) {
        this.status = status;
        this.count = count;
    }

}
