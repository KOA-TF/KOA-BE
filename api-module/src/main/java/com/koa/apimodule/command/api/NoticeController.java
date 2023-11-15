package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.notice.application.service.NoticeFindUseCase;
import com.koa.coremodule.notice.application.service.NoticeSaveUseCase;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeFindUseCase noticeFindUseCase;
    private final NoticeSaveUseCase noticeSaveUseCase;
    private final MemberUtils memberUtils;

    /**
     * 공지 전체 조회
     */
    @GetMapping(value = "")
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

        Member memberRequest = memberUtils.getAccessMember();
        request.setMemberId(memberRequest.getId());

        Long noticeId = noticeSaveUseCase.saveNotice(request, multipartFile);
        return ApplicationResponse.ok(noticeId, "공지 작성에 성공했습니다.");
    }

    /**
     * 공지 수정
     */
    @PatchMapping(value = "")
    public ApplicationResponse<Long> updateNotice(
            @RequestPart(value = "dto") NoticeRequest request,
            @RequestPart(value = "file") MultipartFile multipartFile) {

        Member memberRequest = memberUtils.getAccessMember();
        request.setMemberId(memberRequest.getId());

        Long noticeId = noticeSaveUseCase.updateNotice(request, multipartFile);
        return ApplicationResponse.ok(noticeId, "공지 수정에 성공했습니다.");
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
    public ApplicationResponse<NoticeDetailResponse> noticeDetail(
            @PathVariable Long noticeId) {

        Member memberRequest = memberUtils.getAccessMember();

        NoticeDetailResponse response = noticeSaveUseCase.selectNoticeDetail(memberRequest.getId(), noticeId);
        return ApplicationResponse.ok(response, "공지 상세 조회에 성공했습니다.");
    }

}
