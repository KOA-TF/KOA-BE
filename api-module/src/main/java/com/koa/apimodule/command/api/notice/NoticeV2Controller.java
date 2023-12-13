package com.koa.apimodule.command.api.notice;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.dto.NoticeV2ListResponse;
import com.koa.coremodule.notice.application.dto.NoticeV2Request;
import com.koa.coremodule.notice.application.service.NoticeFindUseCase;
import com.koa.coremodule.notice.application.service.NoticeSaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/notice")
public class NoticeV2Controller {

    private final NoticeFindUseCase noticeFindUseCase;
    private final NoticeSaveUseCase noticeSaveUseCase;
    private final MemberUtils memberUtils;

    /**
     * 공지 전체 조회
     */
    @GetMapping
    public ApplicationResponse<List<NoticeV2ListResponse>> selectAllNotice() {

        Member memberRequest = memberUtils.getAccessMember();

        List<NoticeV2ListResponse> response = noticeFindUseCase.selectNoticeV2(memberRequest.getId());
        return ApplicationResponse.ok(response, "공지 전체 조회에 성공했습니다.");
    }

    /**
     * 공지 작성
     */
    @PostMapping(value = "")
    public ApplicationResponse<Long> saveNotice(
            @RequestPart(value = "dto") NoticeV2Request request,
            @RequestPart(value = "file") MultipartFile multipartFile) {

        Long noticeId = noticeSaveUseCase.saveNoticeV2(request, multipartFile);
        return ApplicationResponse.ok(noticeId, "공지 작성에 성공했습니다.");
    }

}
