package com.koa.coremodule.attend.application.dto;

import com.koa.coremodule.attend.domain.entity.AttendStatus;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AttendList {

    private Long attendId;
    private String curriculum;
    private String date;
    private String time;
    private AttendStatus status;

}
