package com.koa.coremodule.notice.domain.repository.projection;

import static com.koa.coremodule.curriculum.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.member.domain.entity.QMemberDetail.memberDetail;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class NoticeV2DetailListProjection {

    public static final ConstructorExpression<NoticeV2DetailListProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(NoticeV2DetailListProjection.class,
                    notice.id,
                    member.name,
                    memberDetail.profileImage,
                    notice.title,
                    notice.content,
                    notice.createdAt,
                    curriculum.curriculumName,
                    noticeTeam.teamName
            );

    private final Long noticeId;
    private final String name;
    private final String profileImage;
    private final String title;
    private final String content;
    private final LocalDateTime date;
    private final String curriculumName;
    private final String teamName;

    @QueryProjection
    public NoticeV2DetailListProjection(Long noticeId, String name, String profileImage, String title, String content, LocalDateTime date, String curriculumName, String teamName) {
        this.noticeId = noticeId;
        this.name = name;
        this.profileImage = profileImage;
        this.title = title;
        this.content = content;
        this.date = date;
        this.curriculumName = curriculumName;
        this.teamName = teamName;
    }
}
