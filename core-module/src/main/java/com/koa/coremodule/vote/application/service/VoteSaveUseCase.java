package com.koa.coremodule.vote.application.service;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.vote.application.dto.VoteRequest;
import com.koa.coremodule.vote.application.mapper.VoteItemRecordMapper;
import com.koa.coremodule.vote.application.mapper.VoteMapper;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import com.koa.coremodule.vote.domain.service.VoteFindService;
import com.koa.coremodule.vote.domain.service.VoteSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteSaveUseCase {

    private final MemberUtils memberUtils;
    private final VoteSaveService voteSaveService;
    private final VoteFindService voteFindService;
    private final NoticeQueryService noticeQueryService;
    private final MemberQueryService memberQueryService;
    private final VoteMapper voteMapper;

    public Long saveVote(VoteRequest voteRequest) {

        Member memberRequest = memberUtils.getAccessMember();

        final ArrayList<VoteItem> voteItems = new ArrayList<>();
        // 투표 제목 저장
        Vote voteEntity = voteMapper.toVoteEntity(voteRequest.title());
        //1차 저장 -> ID 생성을 위해서
        Vote vote = voteSaveService.saveVote(voteEntity);

        // 투표 항목 개수 체크 후 각자 저장
        List<String> titles = voteRequest.item();
        for (String i : titles) {
            //vote Item enttiy 생성
            VoteItem voteItemEntity = voteMapper.toVoteItemEntity(i);
            voteItemEntity.setVote(vote);
            voteItems.add(voteItemEntity);
            voteSaveService.saveVoteItem(voteItemEntity);
        }
        //vote Item을 vote를 통해서 저장
        vote.setVoteItems(voteItems);

        //notice 조회 후 저장
        Notice notice = noticeQueryService.findByNoticeId(voteRequest.noticeId());
        vote.setNotice(notice);

        voteSaveService.saveVote(vote);

        return vote.getId();
    }

    public Long attendVote(Long voteItemId) {

        Member memberRequest = memberUtils.getAccessMember();
        VoteItem voteItem = voteFindService.findVoteItemById(voteItemId);
        Member member = memberQueryService.findMemberById(memberRequest.getId());

        VoteItemRecord voteItemRecordRequest = VoteItemRecordMapper.toVoteItemRecord(voteItem, member);
        VoteItemRecord voteItemRecord = voteSaveService.saveVoteRecord(voteItemRecordRequest);

        return voteItemRecord.getId();
    }

}
