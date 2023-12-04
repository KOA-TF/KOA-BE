package com.koa.coremodule.vote.application.mapper;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;

public final class VoteItemRecordMapper {
    private VoteItemRecordMapper() {
    }

    public static VoteItemRecord toVoteItemRecord(VoteItem item, Member member) {
        return VoteItemRecord.builder()
                .voteItem(item)
                .member(member)
                .build();
    }
}
