package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRecordRepository extends JpaRepository<VoteItemRecord, Long> {

    VoteItemRecord findVoteItemRecordByVoteItemId(Long voteItemId);

}
