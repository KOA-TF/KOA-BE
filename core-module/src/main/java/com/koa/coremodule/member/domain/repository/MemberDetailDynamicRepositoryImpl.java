package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.entity.QMember;
import com.koa.coremodule.member.domain.entity.QMemberDetail;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDetailDynamicRepositoryImpl implements MemberDetailDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberDetail> findAllSortedByPartAndName() {
        final QMemberDetail memberDetail = QMemberDetail.memberDetail;
        final QMember member = memberDetail.member;

        return jpaQueryFactory.selectFrom(memberDetail)
                .join(member).fetchJoin()
                .orderBy(
                        Expressions.numberTemplate(Integer.class,
                                "CASE {0} " +
                                        "WHEN 'PLANNING' THEN 1 " +
                                        "WHEN 'DESIGN' THEN 2 " +
                                        "WHEN 'DEVELOPMENT' THEN 3 " +
                                        "ELSE 4 END", memberDetail.part).asc(),
                        member.name.asc()
                )
                .fetch();
    }
}
