package com.koa.coremodule.vote.application.service;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.vote.application.dto.VoteItemRequest;
import com.koa.coremodule.vote.application.dto.VoteRequest;
import com.koa.coremodule.vote.application.mapper.VoteMapper;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import com.koa.coremodule.vote.domain.service.VoteSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteSaveUseCase {

    private final MemberUtils memberUtils;
    private final VoteSaveService voteSaveService;
    private final VoteMapper voteMapper;

    public Long saveVote(VoteRequest voteRequest) {

        Member memberRequest = memberUtils.getAccessMember();

        // 투표 제목 저장
        Vote voteEntity = voteMapper.toVoteEntity(voteRequest.title());
        Vote vote = voteSaveService.saveVote(voteEntity);

        // 투표 항목 개수 체크 후 각자 저장
        List<String> titles = voteRequest.item();
        for(String i : titles) {

            VoteItem voteItemEntity = voteMapper.toVoteItemEntity(i);
            VoteItem voteItem = voteSaveService.saveVoteItem(voteItemEntity);

            VoteItemRequest voteItemRequest = VoteItemRequest.builder()
                    .memberId(memberRequest.getId())
                    .voteItemId(voteItem.getId())
                    .build();

            VoteItemRecord voteItemRecordRequest = voteMapper.toVoteRecordEntity(voteItemRequest);
            VoteItemRecord voteItemRecord = voteSaveService.saveVoteRecord(voteItemRecordRequest);
        }

        return vote.getId();
    }

}
