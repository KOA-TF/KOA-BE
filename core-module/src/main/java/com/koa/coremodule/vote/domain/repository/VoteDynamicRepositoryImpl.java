package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.vote.domain.repository.projection.VoteItemProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koa.coremodule.member.domain.entity.QMember.member;
import static com.koa.coremodule.vote.domain.entity.QVoteItem.voteItem;
import static com.koa.coremodule.vote.domain.entity.QVoteItemRecord.voteItemRecord;

@RequiredArgsConstructor
public class VoteDynamicRepositoryImpl implements VoteDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VoteItemProjection> findVoteItems() {
        return jpaQueryFactory.select(VoteItemProjection.CONSTRUCTOR_EXPRESSION)
                .from(voteItem)
                .leftJoin(voteItemRecord)
                .on(voteItem.id.eq(voteItemRecord.voteItem.id))
                .groupBy(voteItem.voteItemName)
                .fetch();
    }

    @Override
    public Member findVoteMemberByMemberId(Long memberId, Long recordId) {
        return jpaQueryFactory.select(member)
                .from(voteItemRecord)
                .join(member)
                .on(voteItemRecord.member.id.eq(member.id))
                .where(voteItemRecord.member.id.eq(memberId).and(voteItemRecord.id.eq(recordId)))
                .fetchOne();
    }

}
