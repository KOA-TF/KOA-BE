package com.koa.coremodule.vote.domain.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import com.koa.coremodule.vote.domain.exception.VoteException;
import com.koa.coremodule.vote.domain.repository.VoteItemRepository;
import com.koa.coremodule.vote.domain.repository.VoteRecordRepository;
import com.koa.coremodule.vote.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public void finishVote(Long voteId) {

        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new VoteException(Error.VOTE_NOT_FOUND));
        vote.updateStatus();
    }

}
