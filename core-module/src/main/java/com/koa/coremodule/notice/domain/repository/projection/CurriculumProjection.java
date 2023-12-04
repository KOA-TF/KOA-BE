package com.koa.coremodule.notice.domain.repository.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CurriculumProjection {
    private Long curriculumId;
    private String curriculumName;
    private String title;

    @QueryProjection
    public CurriculumProjection(Long curriculumId, String curriculumName, String title) {
        this.curriculumId = curriculumId;
        this.curriculumName = curriculumName;
        this.title = title;
    }
}
