package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.QNotice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.repository.projection.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.member.domain.entity.QMemberDetail.memberDetail;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeView.noticeView;

@RequiredArgsConstructor
public class NoticeDynamicRepositoryImpl implements NoticeDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeListProjection> findAllNotice() {
        return jpaQueryFactory.select(new QNoticeListProjection(
                                notice.id,
                                notice.title,
                                notice.content,
                                notice.createdAt,
                                notice.noticeImage.imageUrl,
                                notice.curriculum.curriculumName,
                                notice.noticeTeam.teamName
                        )
                )
                .from(notice)
                .orderBy(notice.createdAt.desc())
                .fetch();
    }

    @Override
    public NoticeDetailListProjection findAllNoticeDetail(Long noticeId) {
        return jpaQueryFactory.select(NoticeDetailListProjection.CONSTRUCTOR_EXPRESSION)
                .from(notice)
                .join(member).on(notice.member.id.eq(member.id))
                .join(memberDetail).on(memberDetail.member.id.eq(member.id))
                .where(notice.id.eq(noticeId))
                .orderBy(notice.createdAt.desc())
                .fetchOne();
    }

    @Override
    public ViewType findViewYn(NoticeViewRequest request) {
        return jpaQueryFactory.select(noticeView.view)
                .from(noticeView)
                .where(noticeView.member.id.eq(request.memberId()).and(noticeView.notice.id.eq(request.noticeId())))
                .fetchOne();
    }

    @Override
    public List<CurriculumProjection> findByCurriculum() {

        final QNotice subNotice = new QNotice("sub");

        return jpaQueryFactory
                .selectDistinct(new QCurriculumProjection(
                        curriculum.id,
                        curriculum.curriculumName,
                        notice.title
                ))
                .from(curriculum)
                .leftJoin(notice).on(curriculum.id.eq(notice.curriculum.id)
                        .and(notice.createdAt.eq(JPAExpressions
                                .select(subNotice.createdAt.max())
                                .from(subNotice)
                                .where(subNotice.curriculum.id.eq(notice.curriculum.id)))
                        )).fetch();
    }

    @Override
    public List<Notice> selectNoticeByCurriculum(Long curriculumId) {
        return jpaQueryFactory.selectFrom(notice)
                .join(curriculum).on(notice.curriculum.id.eq(curriculumId))
                .orderBy(notice.createdAt.desc())
                .fetch();
    }

}
