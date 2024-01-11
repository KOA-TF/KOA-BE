package com.koa.coremodule.vote.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class VoteStatus {

    private Long voteId;
    private Boolean voteAttendYn;
    private Integer total;
    private String title;
    private List<VoteItemStatus> items;

    @Data
    @Builder
    @Jacksonized
    public static class VoteItemStatus {
        private Long voteItemId;
        private String item;
        private Integer count;
        private List<MemberList> members;
    }

    @Data
    @Builder
    @Jacksonized
    public static class MemberList {
        private Long memberId;
        private String name;
        private String profileImageUrl;
    }

}
