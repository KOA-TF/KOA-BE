package com.koa.coremodule.fcm.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AlarmLists {

    private Long alarmId;
    private Long noticeId;
    private Long commentId;
    private String title;
    private String content;
    private String date;
    private Boolean viewYn;

}
