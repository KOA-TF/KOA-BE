package com.koa.coremodule.vote.domain.service;

import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import com.koa.coremodule.vote.domain.repository.VoteItemRepository;
import com.koa.coremodule.vote.domain.repository.VoteRecordRepository;
import com.koa.coremodule.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteSaveService {

    private final VoteRepository voteRepository;
    private final VoteRecordRepository voteRecordRepository;
    private final VoteItemRepository voteItemRepository;

    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public VoteItem saveVoteItem(VoteItem voteItem) {
        return voteItemRepository.save(voteItem);
    }

    public VoteItemRecord saveVoteRecord(VoteItemRecord voteItemRecord) {
        return voteRecordRepository.save(voteItemRecord);
    }

}
