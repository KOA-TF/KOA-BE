package com.koa.coremodule.admin.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AdminReportList {

    private Long no;
    private String writer;
    private String category;
    private String comment;
    private Boolean isHided;

}
