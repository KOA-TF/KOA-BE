package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeFindUseCase;
import com.koa.coremodule.notice.application.service.NoticeSaveUseCase;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.repository.CurriculumRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import com.koa.coremodule.notice.domain.repository.NoticeTeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeFindUseCase noticeFindUseCase;
    private final NoticeSaveUseCase noticeSaveUseCase;
    private final MemberUtils memberUtils;
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
        final Member member2 = Member.builder()
                .authority(Authority.MEMBER)
                .email("test@gmail.com")
                .password("001215")
                .name("test")
                .build();
        memberRepository.save(member2);

        final Curriculum curriculum = Curriculum.builder()
                .curriculumName("기업프로젝트1")
                .build();
        curriculumRepository.save(curriculum);
        final Curriculum curriculum2 = Curriculum.builder()
                .curriculumName("기업프로젝트2")
                .build();
        curriculumRepository.save(curriculum2);
        final Curriculum curriculum3 = Curriculum.builder()
                .curriculumName("기업프로젝트3")
                .build();
        curriculumRepository.save(curriculum3);

        final NoticeTeam noticeTeam = NoticeTeam.builder()
                .teamName("경영총괄팀")
                .build();
        noticeTeamRepository.save(noticeTeam);
    }

    /**
     * 공지 전체 조회
     */
    @GetMapping
    public ApplicationResponse<List<NoticeListResponse>> selectAllNotice() {

        Member memberRequest = memberUtils.getAccessMember();

        List<NoticeListResponse> response = noticeFindUseCase.selectNotice(memberRequest.getId());
        return ApplicationResponse.ok(response, "공지 전체 조회에 성공했습니다.");
    }

    /**
     * 커리큘럼 공지 조회
     */
    @GetMapping(value = "/curriculum")
    public ApplicationResponse<List<CurriculumResponse>> selectCurriculum() {

        List<CurriculumResponse> response = noticeFindUseCase.selectCurriculum();

        return ApplicationResponse.ok(response, "커리큘럼 공지 조회에 성공했습니다.");
    }

    /**
     * 토글 누를 시 공지 조회
     */
    @GetMapping(value = "/curriculum/list")
    public ApplicationResponse<List<CurriculumListResponse>> selectCurriculumList(
            @RequestParam Long curriculumId
    ) {

        List<CurriculumListResponse> responses = noticeFindUseCase.selectCurriculumList(curriculumId);

        return ApplicationResponse.ok(responses, "공지 조회에 성공했습니다.");
    }

    /**
     * 공지 작성
     */
    @PostMapping(value = "")
    public ApplicationResponse<Long> saveNotice(
            @RequestPart(value = "dto") NoticeRequest request,
            @RequestPart(value = "file") MultipartFile multipartFile) {

        Long noticeId = noticeSaveUseCase.saveNotice(request, multipartFile);
        return ApplicationResponse.ok(noticeId, "공지 작성에 성공했습니다.");
    }

    /**
     * 공지 수정
     */
    @PatchMapping(value = "")
    public ApplicationResponse<Long> updateNotice(
            @RequestPart(value = "dto") NoticeUpdateRequest request,
            @RequestPart(value = "file") MultipartFile multipartFile) {

        Member memberRequest = memberUtils.getAccessMember();
        request.setMemberId(memberRequest.getId());

        final Long updatedId = noticeSaveUseCase.updateNotice(request, multipartFile);
        return ApplicationResponse.ok(updatedId, "공지 수정에 성공했습니다.");
    }

    /**
     * 공지 삭제
     */
    @DeleteMapping(value = "/{noticeId}")
    public ApplicationResponse<Void> deleteNotice(
            @PathVariable Long noticeId) {

        noticeSaveUseCase.deleteNotice(noticeId);
        return ApplicationResponse.ok(null, "공지 삭제에 성공했습니다.");
    }

    /**
     * 공지 상세 조회 (내용)
     */
    @GetMapping(value = "/{noticeId}/detail")
    public ApplicationResponse<NoticeListResponse> noticeDetail(
            @PathVariable Long noticeId) {

        Member memberRequest = memberUtils.getAccessMember();

        NoticeListResponse response = noticeSaveUseCase.selectNoticeDetail(memberRequest.getId(), noticeId);
        return ApplicationResponse.ok(response, "공지 상세 조회에 성공했습니다.");
    }

}
