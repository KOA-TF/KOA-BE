package com.koa.coremodule.notice.repository;

import com.koa.coremodule.notice.application.dto.CurriculumListRequest;
import com.koa.coremodule.notice.application.dto.NoticeListRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeView.noticeView;

@RequiredArgsConstructor
public class NoticeDynamicRepositoryImpl implements NoticeDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<NoticeListProjection> findAllNotice(NoticeListRequest request) {
        return jpaQueryFactory.select(NoticeListProjection.CONSTRUCTOR_EXPRESSION)
                .from(notice)
                .join(member).on(notice.id.eq(request.memberId()))
                .orderBy(notice.createdAt.desc())
                .fetch();
    }

    @Override
    public Integer findViewYn(NoticeViewRequest request) {
        return jpaQueryFactory.select(noticeView.view)
                .from(noticeView)
                .where(noticeView.member.id.eq(request.memberId()).and(noticeView.notice.id.eq(request.noticeId())))
                .fetchOne();
    }

    @Override
    public List<CurriculumProjection> findByCurriculum() {

        return jpaQueryFactory.select(CurriculumProjection.CONSTRUCTOR_EXPRESSION)
                .from(curriculum)
                .join(notice).on(notice.id.eq(curriculum.notice.id))
                .join(member).on(member.id.eq(notice.member.id))
                .where(notice.createdAt.eq(
                        JPAExpressions.select(notice.createdAt.max())
                                .from(notice)
                                .where(notice.curriculum.id.eq(curriculum.id))
                                .fetchOne()))
                .orderBy(curriculum.curriculumName.asc())
                .fetch();
    }

    @Override
    public List<Notice> selectNoticeByCurriculum(CurriculumListRequest request) {
        return jpaQueryFactory.selectFrom(notice)
                .join(curriculum).on(notice.id.eq(request.curriculumId()))
                .orderBy(notice.createdAt.desc())
                .fetch();
    }
}
