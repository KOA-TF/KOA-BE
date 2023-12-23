package com.koa.coremodule.vote.application.service;

import com.koa.coremodule.vote.application.dto.VoteStatus;
import com.koa.coremodule.vote.domain.service.VoteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteFindUseCase {

    private final VoteQueryService voteQueryService;

    public VoteStatus findVoteStatus(Long noticeId) {

        return voteQueryService.findVoteStatus(noticeId);
    }

}
