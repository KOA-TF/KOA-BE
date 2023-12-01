package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRecordRepository extends JpaRepository<VoteItemRecord, Long> {

    List<VoteItemRecord> findVoteItemRecordByVoteItemId(Long voteItemId);

}
