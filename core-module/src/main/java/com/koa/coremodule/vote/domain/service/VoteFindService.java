package com.koa.coremodule.vote.domain.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.vote.application.dto.VoteStatus;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import com.koa.coremodule.vote.domain.repository.VoteItemRepository;
import com.koa.coremodule.vote.domain.repository.VoteRecordRepository;
import com.koa.coremodule.vote.domain.repository.VoteRepository;
import com.koa.coremodule.vote.domain.repository.projection.VoteItemProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteFindService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;

    public VoteStatus findVoteStatus(Long noticeId) {

        VoteStatus voteStatus = new VoteStatus();
        List<VoteStatus.VoteItemStatus> voteItemStatusList = new ArrayList<>();

        // 제목, id 기본정보 넣기
        Vote voteList = findVoteByNoticeId(noticeId);
        voteStatus.setVoteId(voteList.getId());
        voteStatus.setTitle(voteList.getVoteTitle());

        List<VoteItemProjection> voteItemList = voteRepository.findVoteItems();

        // 각 항목들 개수 현황 정보 넣기
        for (VoteItemProjection v : voteItemList) {

            VoteStatus.VoteItemStatus voteItemStatus = VoteStatus.VoteItemStatus.builder()
                    .item(v.getItem())
                    .count(Math.toIntExact(v.getCount()))
                    .build();

            List<VoteStatus.MemberList> memberLists = new ArrayList<>(); // 항목별로 멤버 초기화

            if (v.getCount() >= 1) {

                // 항목별 멤버 명단 나열
                Optional<VoteItem> voteItem = findVoteItemByItem(v.getItem());
                List<VoteItemRecord> voteItemRecord = findVoteItemRecordById(voteItem.get().getId());

                for(VoteItemRecord vr : voteItemRecord) {
                    VoteStatus.MemberList memberListBuilder = VoteStatus.MemberList.builder()
                            .memberId(vr.getMember().getId())
                            .build();

                    // 이름 넣어주기
                    Member member = voteRepository.findVoteMemberByMemberId(memberListBuilder.getMemberId(), vr.getId());
                    memberListBuilder.setName(member.getName());

                    memberLists.add(memberListBuilder);
                }

                voteItemStatus.setVoteItemId(voteItem.get().getId());
                voteItemStatus.setMembers(memberLists);

                voteItemStatusList.add(voteItemStatus);
            } else {

                Optional<VoteItem> voteItem = findVoteItemByItem(v.getItem());
                voteItemStatus.setVoteItemId(voteItem.get().getId());

                voteItemStatusList.add(voteItemStatus);
            }
        }

        voteStatus.setItems(voteItemStatusList);

        return voteStatus;
    }

    public Vote findVoteByNoticeId(Long noticeId) {

        Vote vote = voteRepository.findVoteByNoticeId(noticeId);

        if (vote != null) {
            return vote;
        } else {
            throw new BusinessException(Error.VOTE_NOT_FOUND);
        }
    }

    public VoteItem findVoteItemById(Long voteItemId) {
        return voteItemRepository.findById(voteItemId).orElseThrow(() -> new BusinessException(Error.VOTE_ITEM_NOT_FOUND));
    }

    public Optional<VoteItem> findVoteItemByItem(String item) {
        return voteItemRepository.findVoteItemByVoteItemName(item);
    }

    public List<VoteItemRecord> findVoteItemRecordById(Long voteItemId) {
        return voteRecordRepository.findVoteItemRecordByVoteItemId(voteItemId);
    }

}
