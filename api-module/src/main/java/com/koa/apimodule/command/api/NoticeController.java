package com.koa.apimodule.command.api;

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
    public ResponseEntity<List<NoticeListResponse>> selectAllNotice() {

        Member memberRequest = memberUtils.getAccessMember();

        List<NoticeListResponse> response = noticeFindUseCase.selectNotice(memberRequest.getId());

        return ResponseEntity.ok(response);
    }

    /**
     * 커리큘럼 공지 조회
     */
    @GetMapping(value = "/curriculum")
    public ResponseEntity<List<CurriculumResponse>> selectCurriculum() {

        List<CurriculumResponse> response = noticeFindUseCase.selectCurriculum();

        return ResponseEntity.ok(response);
    }

    /**
     * 토글 누를 시 공지 조회
     */
    @GetMapping(value = "/curriculum/list")
    public ResponseEntity<List<CurriculumListResponse>> selectCurriculumList(
            @RequestParam Long curriculumId
    ) {

        List<CurriculumListResponse> responses = noticeFindUseCase.selectCurriculumList(curriculumId);

        return ResponseEntity.ok(responses);
    }

    /**
     * 공지 작성
     */
    @PostMapping(value = "")
    public ResponseEntity<Void> saveNotice(
            @RequestPart(value = "dto") NoticeRequest request,
            @RequestPart(value = "file") MultipartFile multipartFile) {

        Member memberRequest = memberUtils.getAccessMember();
        request.setMemberId(memberRequest.getId());

        Long noticeId = noticeSaveUseCase.saveNotice(request, multipartFile);
        return ResponseEntity.created(URI.create("/notice/" + noticeId)).build();
    }

    /**
     * 공지 상세 조회 (내용)
     */
    @GetMapping(value = "/{noticeId}/detail")
    public ResponseEntity<NoticeDetailResponse> noticeDetail(
            @PathVariable Long noticeId) {

        NoticeDetailResponse response = noticeSaveUseCase.selectNoticeDetail(noticeId);
        return ResponseEntity.ok(response);
    }

}
