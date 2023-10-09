package com.koa.apimodule.command.api.notice.presentation;

import com.koa.apimodule.command.api.notice.service.NoticeFindUseCase;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeFindUseCase noticeFindUseCase;
    private final MemberUtils memberUtils;

    /**
     * 공지 전체 조회
     */
    @GetMapping(value = "")
    public ResponseEntity<List<NoticeListResponse>> selectAllNotice() {

        Member memberRequest = memberUtils.getAccessMember();

        NoticeListRequest request = new NoticeListRequest(memberRequest.getId());
        List<NoticeListResponse> response = noticeFindUseCase.selectNotice(request);

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

        CurriculumListRequest request = new CurriculumListRequest(curriculumId);
        List<CurriculumListResponse> responses = noticeFindUseCase.selectCurriculumList(request);

        return ResponseEntity.ok(responses);
    }

    /**
     * 공지 상세 조회 (내용)
     */

    /**
     * 공지 작성
     */

}
