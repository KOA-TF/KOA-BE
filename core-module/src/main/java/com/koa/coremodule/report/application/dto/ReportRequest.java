package com.koa.coremodule.report.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportRequest {

    private Long memberId;
    private Long commentId;
    private String content;

}
