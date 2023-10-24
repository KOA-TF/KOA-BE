package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;
import static com.koa.coremodule.notice.domain.entity.QNotice.notice;
import static com.koa.coremodule.notice.domain.entity.QNoticeImage.noticeImage;
import static com.koa.coremodule.notice.domain.entity.QNoticeTeam.noticeTeam;
import static com.koa.coremodule.notice.domain.entity.QNoticeView.noticeView;

@RequiredArgsConstructor
public class NoticeDynamicRepositoryImpl implements NoticeDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<NoticeListProjection> findAllNotice(Long memberId) {
        return jpaQueryFactory.select(NoticeListProjection.CONSTRUCTOR_EXPRESSION)
                .from(notice)
                .join(member).on(notice.id.eq(memberId))
                .join(noticeTeam).on(noticeTeam.notice.id.eq(notice.id))
                .orderBy(notice.createdAt.desc())
                .fetch();
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

        return jpaQueryFactory.select(CurriculumProjection.CONSTRUCTOR_EXPRESSION)
                .from(curriculum)
                .join(notice).on(notice.curriculum.id.eq(curriculum.id))
                .join(member).on(member.id.eq(notice.member.id))
                .fetch();
    }

    @Override
    public List<Notice> selectNoticeByCurriculum(Long curriculumId) {
        return jpaQueryFactory.selectFrom(notice)
                .join(curriculum).on(notice.id.eq(curriculumId))
                .orderBy(notice.createdAt.desc())
                .fetch();
    }

    @Override
    public NoticeDetailProjection findNoticeDetailById(Long noticeId) {
        return jpaQueryFactory.select(NoticeDetailProjection.CONSTRUCTOR_EXPRESSION)
                .from(notice)
                .join(noticeTeam).on(noticeTeam.notice.id.eq(notice.id))
                .join(noticeImage).on(noticeImage.notice.id.eq(notice.id))
                .where(notice.id.eq(noticeId))
                .fetchOne();
    }
}
