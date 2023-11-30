package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteDynamicRepository {

    Vote findVoteByNoticeId(Long noticeId);

}
