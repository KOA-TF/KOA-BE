package com.koa.coremodule.fcm.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class AlarmLists {

    private Long alarmId;
    private String title;
    private String content;
    private LocalDateTime date;
    private Boolean viewYn;

}
