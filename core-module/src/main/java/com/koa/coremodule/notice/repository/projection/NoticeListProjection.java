package com.koa.coremodule.notice.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;

@AllArgsConstructor
@Getter
public class NoticeListProjection {

    public static final ConstructorExpression<NoticeListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeListProjection.class,
                    notice.id,
                    notice.title,
                    notice.content,
                    curriculum.curriculumName,
                    noticeTeam.teamName,
                    notice.createdAt
            );

    private Long noticeId;
    private String title;
    private String content;
    private String curriculumName;
    private String teamName;
    private LocalDate date;

}
