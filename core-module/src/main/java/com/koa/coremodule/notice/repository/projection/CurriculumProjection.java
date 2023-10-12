package com.koa.coremodule.notice.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;

@AllArgsConstructor
@Getter
public class CurriculumProjection {

    public static final ConstructorExpression<CurriculumProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(CurriculumProjection.class,
                    curriculum.id,
                    curriculum.curriculumName,
                    notice.title,
                    member.name
            );

    private Long curriculumId;
    private String curriculumName;
    private String title;
    private String name;

}
