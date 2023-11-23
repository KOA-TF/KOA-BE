package com.koa.coremodule.notice.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CurriculumResponse {

    private Long curriculumId;
    private String curriculumName;
    private String title;
}
