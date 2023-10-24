package com.koa.coremodule.notice.domain.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeImage.noticeImage;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;

@AllArgsConstructor
@Getter
public class NoticeDetailProjection {

    public static final ConstructorExpression<NoticeDetailProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeDetailProjection.class,
                    notice.id,
                    notice.curriculum.curriculumName,
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
