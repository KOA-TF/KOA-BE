package com.koa.coremodule.notice.repository;

import com.koa.coremodule.notice.application.dto.NoticeListRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeView.noticeView;

@RequiredArgsConstructor
public class NoticeDynamicRepositoryImpl implements NoticeDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<NoticeListProjection> findAllNotice(NoticeListRequest request) {
        return jpaQueryFactory.select(NoticeListProjection.CONSTRUCTOR_EXPRESSION)
                .from(notice)
                .join(member).on(notice.id.eq(member.id))
                .orderBy(notice.id.desc())
                .fetch();
    }

    @Override
    public Integer findViewYn(NoticeViewRequest request) {
        return jpaQueryFactory.select(noticeView.view)
                .from(noticeView)
                .where(noticeView.member.id.eq(request.memberId()).and(noticeView.notice.id.eq(request.noticeId())))
                .fetchOne();
    }
}
