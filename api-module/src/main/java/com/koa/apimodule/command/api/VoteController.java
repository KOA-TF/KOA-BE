package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.repository.CurriculumRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import com.koa.coremodule.notice.domain.repository.NoticeTeamRepository;
import com.koa.coremodule.vote.application.dto.VoteRequest;
import com.koa.coremodule.vote.application.service.VoteSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vote")
public class VoteController {

    private final VoteSaveUseCase voteSaveUseCase;
    private final MemberRepository memberRepository;
    private final NoticeTeamRepository noticeTeamRepository;
    private final CurriculumRepository curriculumRepository;
    private final NoticeRepository noticeRepository;

    @PostConstruct
    public void test() {
        final Member member = Member.builder()
                .authority(Authority.MEMBER)
                .email("austinan123@gmail.com")
                .password("001215")
                .name("안정후")
                .build();
        memberRepository.save(member);
        final Curriculum curriculum = Curriculum.builder()
                .curriculumName("기업프로젝트")
                .build();
        curriculumRepository.save(curriculum);
        final NoticeTeam noticeTeam = NoticeTeam.builder()
                .teamName("경영총괄팀")
                .build();
        noticeTeamRepository.save(noticeTeam);
        final Notice notice = Notice.builder()
                .member(member)
                .title("sampletitle")
                .content("samplecontent")
                .curriculum(curriculum)
                .build();
        noticeRepository.save(notice);
    }

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

    /**
     * 투표 참여
     */

}
