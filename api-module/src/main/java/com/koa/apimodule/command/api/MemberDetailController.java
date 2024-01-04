package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.request.MemberDetailUpdateRequest;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoListResponse;
import com.koa.coremodule.member.application.dto.response.MemberSearchResponse;
import com.koa.coremodule.member.application.service.MemberDetailChangeUseCase;
import com.koa.coremodule.member.application.service.MemberDetailCreateUseCase;
import com.koa.coremodule.member.application.service.MemberDetailGetUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberDetailController {

    private final MemberDetailCreateUseCase memberDetailCreateUseCase;
    private final MemberDetailChangeUseCase memberDetailChangeUseCase;
    private final MemberDetailGetUseCase memberDetailGetUseCase;

    @PostMapping
    public ApplicationResponse<Void> postMemberDetail(@RequestPart(value = "dto") MemberDetailCreateRequest memberDetailCreateRequest, @RequestPart(value = "file") MultipartFile multipartFile){
        memberDetailCreateUseCase.createMemberDetail(memberDetailCreateRequest, multipartFile);
        return ApplicationResponse.ok(null);
    }

    @PutMapping
    public ApplicationResponse<Void> putMemberDetail(@RequestPart(value = "dto") MemberDetailUpdateRequest memberDetailUpdateRequest, @RequestPart(value = "file") MultipartFile multipartFile){
        memberDetailChangeUseCase.updateMemberDetail(memberDetailUpdateRequest, multipartFile);
        return ApplicationResponse.ok(null);
    }

    @GetMapping("/info/detail")
    public ApplicationResponse<MemberDetailInfoResponse> getMemberDetailInfo() {
        MemberDetailInfoResponse response = memberDetailGetUseCase.getMemberDetailInfo();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/info/list")
    public ApplicationResponse<List<MemberInfoListResponse>> getMemberInfoList() {
        List<MemberInfoListResponse> response = memberDetailGetUseCase.getMemberInfoList();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/info/{memberId}")
    public ApplicationResponse<MemberSearchResponse> getMemberSearchInfo(@PathVariable Long memberId) {
        MemberSearchResponse response = memberDetailGetUseCase.getMemberSearchInfo(memberId);
        return ApplicationResponse.ok(response);
    }

}
