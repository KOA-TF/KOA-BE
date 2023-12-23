package com.koa.coremodule.attend.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AttendContent {

    private Integer penalty;
    private Integer present;
    private Integer absent;
    private Integer late;
    private String passYn;

}
