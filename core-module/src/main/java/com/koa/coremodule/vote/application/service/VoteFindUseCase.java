package com.koa.coremodule.vote.application.service;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.vote.application.dto.VoteStatus;
import com.koa.coremodule.vote.application.mapper.VoteMapper;
import com.koa.coremodule.vote.domain.service.VoteFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteFindUseCase {

    private final MemberUtils memberUtils;
    private final VoteFindService voteFindService;
    private final VoteMapper voteMapper;

    public VoteStatus findVoteStatus(Long noticeId) {

        return voteFindService.findVoteStatus(noticeId);
    }

}
