package com.koa.coremodule.vote.domain.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberDetailRepository;
import com.koa.coremodule.member.domain.utils.MemberUtils;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteQueryService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;
    private final MemberDetailRepository memberDetailRepository;
    private final MemberUtils memberUtils;

    public VoteStatus findVoteStatus(Long noticeId) {

        Member myMember = memberUtils.getAccessMember();
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
            Optional<VoteItem> voteItem = findVoteItemByItem(v.getItem());

            if (v.getCount() >= 1) {

                // 항목별 멤버 명단 나열
                List<VoteItemRecord> voteItemRecord = findVoteItemRecordById(voteItem.get().getId());

                for (VoteItemRecord vr : voteItemRecord) {
                    VoteStatus.MemberList memberListBuilder = VoteStatus.MemberList.builder()
                            .memberId(vr.getMember().getId())
                            .build();

                    // 이름 넣어주기
                    Member member = voteRepository.findVoteMemberByMemberId(memberListBuilder.getMemberId(), vr.getId());
                    memberListBuilder.setName(member.getName());

                    memberDetailRepository.findByMemberId(member.getId()).ifPresent(memberDetail -> {
                        memberListBuilder.setProfileImageUrl(memberDetail.getProfileImage());
                    });

                    memberLists.add(memberListBuilder);
                }

                voteItemStatus.setVoteItemId(voteItem.get().getId());
                voteItemStatus.setMembers(memberLists);

            } else {

                voteItemStatus.setVoteItemId(voteItem.get().getId());

            }
            voteItemStatusList.add(voteItemStatus);
        }

        voteStatus.setItems(voteItemStatusList);

        int count = 0;
        Long voteAttendId = null;

        //TODO -- 투표 참여여부 + 투표 전체 참여자
        for (VoteStatus.VoteItemStatus v : voteStatus.getItems()) {

            List<VoteStatus.MemberList> members = v.getMembers();
            int itemCount = 0;

            if (members != null) {

                for (VoteStatus.MemberList m : members) {
                    // 투표 참여여부
                    if (m.getMemberId().equals(myMember.getId())) {
                        voteAttendId = v.getVoteItemId(); // 해당 항목의 ID를 가져옴
                    }

                    itemCount++;
                }
            }

            count += itemCount;
        }

        voteStatus.setVoteAttendId(voteAttendId);
        voteStatus.setTotal(count);

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

    public Vote findVoteByNoticeIdWithEmpty(Long noticeId) {

        Vote vote = voteRepository.findVoteByNoticeId(noticeId);
        return vote;
    }

    public VoteItem findVoteItemById(Long voteItemId) {
        return voteItemRepository.findById(voteItemId).orElseThrow(() -> new BusinessException(Error.VOTE_ITEM_NOT_FOUND));
    }

    public List<VoteItem> findAllVoteItems() {
        return voteItemRepository.findAll();
    }

    public Optional<VoteItem> findVoteItemByItem(String item) {
        return voteItemRepository.findVoteItemByVoteItemName(item);
    }

    public List<VoteItemRecord> findVoteItemRecordById(Long voteItemId) {
        return voteRecordRepository.findVoteItemRecordByVoteItemId(voteItemId);
    }

    public List<VoteItemRecord> findVoteItemRecordByMemberIdAndItemId(Long memberId, Long voteItemId) {
        return voteRecordRepository.findVoteItemRecordByMemberIdAndVoteItemId(memberId, voteItemId);
    }

}
