package com.koa.coremodule.vote.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteItemRequest {

    private Long voteItemId;
    private Long memberId;

}
