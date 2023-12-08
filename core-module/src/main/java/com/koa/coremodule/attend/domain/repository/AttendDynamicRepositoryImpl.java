package com.koa.coremodule.attend.domain.repository;

import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.attend.domain.entity.QAttend.attend;
import static com.koa.coremodule.notice.domain.entity.QCurriculum.curriculum;

@RequiredArgsConstructor
public class AttendDynamicRepositoryImpl implements AttendDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AttendListProjection> getAttendList(Long memberId) {
        return jpaQueryFactory.select(AttendListProjection.CONSTRUCTOR_EXPRESSION)
                .from(attend)
                .leftJoin(curriculum)
                .on(attend.curriculum.id.eq(curriculum.id))
                .where(attend.member.id.eq(memberId))
                .fetch();
    }

}
