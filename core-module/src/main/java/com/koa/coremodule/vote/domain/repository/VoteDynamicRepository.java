package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.vote.domain.repository.projection.VoteItemProjection;

import java.util.List;

public interface VoteDynamicRepository {

    List<VoteItemProjection> findVoteItems();

    Member findVoteMemberByMemberId(Long memberId, Long recordId);

}
