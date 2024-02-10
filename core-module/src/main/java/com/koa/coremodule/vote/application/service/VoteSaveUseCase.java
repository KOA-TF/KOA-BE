package com.koa.coremodule.vote.application.service;

import com.koa.commonmodule.exception.Error;
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
import com.koa.coremodule.vote.domain.entity.VoteStatusCheck;
import com.koa.coremodule.vote.domain.exception.VoteException;
import com.koa.coremodule.vote.domain.service.VoteQueryService;
import com.koa.coremodule.vote.domain.service.VoteSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteSaveUseCase {

    private final MemberUtils memberUtils;
    private final VoteSaveService voteSaveService;
    private final VoteQueryService voteQueryService;
    private final NoticeQueryService noticeQueryService;
    private final MemberQueryService memberQueryService;
    private final VoteMapper voteMapper;

    public Long saveVote(VoteRequest voteRequest) {
        // 투표 제목 저장
        Vote voteEntity = voteMapper.toVoteEntity(voteRequest.title());

        //notice 조회
        Notice notice = noticeQueryService.findByNoticeId(voteRequest.noticeId());

        // Vote 엔티티 생성
        Vote vote = Vote.builder()
                .voteTitle(voteEntity.getVoteTitle())
                .notice(notice)
                .status(VoteStatusCheck.PRESENT)
                .build();

        // Vote 엔티티 저장
        Vote savedVote = voteSaveService.saveVote(vote);

        // 투표 항목 개수 체크 후 각자 저장
        List<String> titles = voteRequest.item();

        for (String title : titles) {
            VoteItem voteItemEntity = VoteItem.builder()
                    .voteItemName(title)
                    .vote(savedVote) // Vote ID 설정
                    .build();
            voteSaveService.saveVoteItem(voteItemEntity);
        }

        return vote.getId();
    }

    public Long attendVote(Long voteItemId) {

        Member memberRequest = memberUtils.getAccessMember();
        VoteItem voteItem = voteQueryService.findVoteItemById(voteItemId);
        Member member = memberQueryService.findMemberById(memberRequest.getId());

        VoteItemRecord voteItemRecord;

        if (voteQueryService.findVoteItemRecordByMemberIdAndItemId(member.getId(), voteItemId) != null) {
            throw new VoteException(Error.VOTE_ITEM_RECORD_NOT_FOUND);
        } else {
            VoteItemRecord voteItemRecordRequest = VoteItemRecordMapper.toVoteItemRecord(voteItem, member);
            voteItemRecord = voteSaveService.saveVoteRecord(voteItemRecordRequest);
        }

        return voteItemRecord.getId();
    }

    public void finishVote(Long voteId) {

        voteSaveService.finishVote(voteId);
    }

}
