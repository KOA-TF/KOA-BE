package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.vote.application.dto.VoteRequest;
import com.koa.coremodule.vote.application.service.VoteSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vote")
public class VoteController {

    public final VoteSaveUseCase voteSaveUseCase;

    /**
     * 투표 생성
     */
    @PostMapping(value = "")
    public ApplicationResponse<Long> makeVote(@RequestBody VoteRequest voteRequest) {

        Long voteId = voteSaveUseCase.saveVote(voteRequest);
        return ApplicationResponse.ok(voteId, "투표 생성을 완료했습니다.");
    }

    /**
     * 투표 현황 조회 - 명단까지 리스트로 전달 필요
     */

    /**
     * 투표 참여
     */

}
