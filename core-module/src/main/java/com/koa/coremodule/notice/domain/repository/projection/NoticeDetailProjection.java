package com.koa.coremodule.notice.domain.repository.projection;

import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeImage.noticeImage;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;
import static com.koa.coremodule.curriculum.domain.entity.QCurriculum.curriculum;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoticeDetailProjection {

    public static final ConstructorExpression<NoticeDetailProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeDetailProjection.class,
                    notice.id,
                    curriculum.curriculumName,
                    notice.title,
                    notice.content,
                    noticeImage.imageUrl,
                    noticeTeam.teamName
            );

    private Long noticeId;
    private String curriculumName;
    private String title;
    private String content;
    private String imageUrl;
    private String teamName;

}
