package com.koa.coremodule.notice.domain.repository.projection;

import static com.koa.coremodule.curriculum.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeImage.noticeImage;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class NoticeListProjection {

    public static final ConstructorExpression<NoticeListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeListProjection.class,
                    notice.id,
                    notice.title,
                    notice.content,
                    notice.createdAt,
                    noticeImage.imageUrl,
                    curriculum.curriculumName,
                    noticeTeam.teamName
            );

    private final Long noticeId;
    private final String title;
    private final String content;
    private final LocalDateTime date;
    private final String imageUrl;
    private final String curriculumName;
    private final String teamName;

    @QueryProjection
    public NoticeListProjection(Long noticeId, String title, String content, LocalDateTime date, String imageUrl, String curriculumName, String teamName) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.imageUrl = imageUrl;
        this.curriculumName = curriculumName;
        this.teamName = teamName;
    }
}
