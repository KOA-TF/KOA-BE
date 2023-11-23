package com.koa.coremodule.notice.application.mapper;

import com.koa.coremodule.notice.application.dto.CurriculumResponse;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;

public final class CurriculumMapper {
    private CurriculumMapper() {
    }

    public static CurriculumResponse toResponse(CurriculumProjection curriculum) {
        if (curriculum == null) return CurriculumResponse.builder().build();
        return CurriculumResponse.builder()
                .curriculumId(curriculum.getCurriculumId())
                .curriculumName(curriculum.getCurriculumName())
                .title(curriculum.getTitle())
                .build();
    }
}
