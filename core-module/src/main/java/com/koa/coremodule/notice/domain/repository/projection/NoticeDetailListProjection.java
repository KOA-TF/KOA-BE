package com.koa.coremodule.notice.domain.repository.projection;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.member.domain.entity.QMemberDetail.memberDetail;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeImage.noticeImage;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;


@Getter
public class NoticeDetailListProjection {

    public static final ConstructorExpression<NoticeDetailListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeDetailListProjection.class,
                    notice.id,
                    member.name,
                    memberDetail.profileImage,
                    notice.title,
                    notice.content,
                    notice.createdAt,
                    noticeImage.imageUrl,
                    curriculum.curriculumName,
                    noticeTeam.teamName
            );

    private final Long noticeId;
    private final String name;
    private final String profileImage;
    private final String title;
    private final String content;
    private final LocalDateTime date;
    private final String imageUrl;
    private final String curriculumName;
    private final String teamName;

    @QueryProjection
    public NoticeDetailListProjection(Long noticeId, String name, String profileImage, String title, String content, LocalDateTime date, String imageUrl, String curriculumName, String teamName) {
        this.noticeId = noticeId;
        this.name = name;
        this.profileImage = profileImage;
        this.title = title;
        this.content = content;
        this.date = date;
        this.imageUrl = imageUrl;
        this.curriculumName = curriculumName;
        this.teamName = teamName;
    }
}
