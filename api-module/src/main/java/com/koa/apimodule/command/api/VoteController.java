package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.vote.application.dto.VoteRequest;
import com.koa.coremodule.vote.application.dto.VoteStatus;
import com.koa.coremodule.vote.application.service.VoteFindUseCase;
import com.koa.coremodule.vote.application.service.VoteSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vote")
public class VoteController {

    private final VoteSaveUseCase voteSaveUseCase;
    private final VoteFindUseCase voteFindUseCase;

    /**
     * 투표 생성
     */
    @PostMapping
    public ApplicationResponse<Long> makeVote(@RequestBody VoteRequest voteRequest) {

        Long voteId = voteSaveUseCase.saveVote(voteRequest);
        return ApplicationResponse.ok(voteId, "투표 생성을 완료했습니다.");
    }

    /**
     * 투표 현황 조회 - 명단까지 리스트로 전달 필요
     */
    @GetMapping
    public ApplicationResponse<VoteStatus> getVoteStatus(Long noticeId) {

        VoteStatus voteStatus = voteFindUseCase.findVoteStatus(noticeId);
        return ApplicationResponse.ok(voteStatus, "투표 현황 조회 완료했습니다.");
    }

    /**
     * 투표 참여
     */
    @PostMapping(value = "/attend")
    public ApplicationResponse<Long> attendVote(Long voteItemId) {

        Long voteItemRecordId = voteSaveUseCase.attendVote(voteItemId);
        return ApplicationResponse.ok(voteItemRecordId, "투표 참여를 완료했습니다.");
    }

    /**
     * 투표 마감 처리
     */

}
