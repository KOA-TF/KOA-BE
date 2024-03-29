package com.koa.apimodule.command.api.notice;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.application.service.NoticeGetUseCase;
import com.koa.coremodule.notice.application.service.NoticeSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeV1Controller {

    private final NoticeGetUseCase noticeGetUseCase;
    private final NoticeSaveUseCase noticeSaveUseCase;
    private final MemberUtils memberUtils;

    /**
     * 공지 전체 조회
     */
    @GetMapping
    public ApplicationResponse<List<NoticeListResponse>> selectAllNotice() {

        Member memberRequest = memberUtils.getAccessMember();

        List<NoticeListResponse> response = noticeGetUseCase.selectNotice(memberRequest.getId());
        return ApplicationResponse.ok(response, "공지 전체 조회에 성공했습니다.");
    }

    /**
     * 커리큘럼 공지 조회
     */
    @GetMapping(value = "/curriculum")
    public ApplicationResponse<List<CurriculumResponse>> selectCurriculum() {

        List<CurriculumResponse> response = noticeGetUseCase.selectCurriculum();

        return ApplicationResponse.ok(response, "커리큘럼 공지 조회에 성공했습니다.");
    }

    /**
     * 토글 누를 시 공지 조회
     */
    @GetMapping(value = "/curriculum/list")
    public ApplicationResponse<List<CurriculumListResponse>> selectCurriculumList(
            @RequestParam Long curriculumId
    ) {

        List<CurriculumListResponse> responses = noticeGetUseCase.selectCurriculumList(curriculumId);

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
            @RequestPart(value = "file") List<MultipartFile> multipartFile) {

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
    public ApplicationResponse<NoticeDetailListResponse> noticeDetail(
            @PathVariable Long noticeId) {

        Member memberRequest = memberUtils.getAccessMember();

        NoticeDetailListResponse response = noticeSaveUseCase.selectNoticeDetail(memberRequest.getId(), noticeId);
        return ApplicationResponse.ok(response, "공지 상세 조회에 성공했습니다.");
    }

    /**
     * 최근 공지 조회
     */
    @GetMapping(value = "/recent")
    public ApplicationResponse<List<NoticePreviewResponse>> getRecentNotice() {

        List<NoticePreviewResponse> response = noticeGetUseCase.getRecentNotice();

        return ApplicationResponse.ok(response, "최근 공지 조회에 성공했습니다.");
    }

}
