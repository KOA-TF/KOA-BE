package com.koa.coremodule.attend.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AttendInfo {

    private Long curriculumId;
    private String curriculumName;
    private Boolean isAttended;
    private String date;
}
