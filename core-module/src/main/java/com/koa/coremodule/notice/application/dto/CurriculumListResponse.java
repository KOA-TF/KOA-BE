package com.koa.coremodule.notice.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Data
@Builder
@Jacksonized
public class CurriculumListResponse {

    private Long noticeId;
    private String title;
    private String content;
    private LocalDate date;

}
