package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.request.MemberPasswordChangeRequest;
import com.koa.coremodule.member.application.dto.response.CheckPasswordResponse;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.dto.response.CheckRegisterResponse;
import com.koa.coremodule.member.application.dto.response.VerifyCodeResponse;
import com.koa.coremodule.member.application.service.EmailVerificationUseCase;
import com.koa.coremodule.member.application.service.MemberDeleteUseCase;
import com.koa.coremodule.member.application.service.MemberDetailCreateUseCase;
import com.koa.coremodule.member.application.service.MemberGetUseCase;
import com.koa.coremodule.member.application.service.MemberCheckUseCase;
import com.koa.coremodule.member.application.service.MemberPasswordChangeUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberGetUseCase memberGetUseCase;
    private final MemberDetailCreateUseCase memberDetailCreateUseCase;
    private final MemberCheckUseCase memberCheckUseCase;
    private final MemberDeleteUseCase memberDeleteUseCase;
    private final EmailVerificationUseCase emailVerificationUseCase;
    private final MemberPasswordChangeUseCase memberPasswordChangeUseCase;

    @GetMapping
    public MemberInfoResponse getMemberInfo(){
        return memberGetUseCase.getMemberInfo();
    }

    @PostMapping
    public ApplicationResponse<Void> postMemberDetail(@RequestPart(value = "dto") MemberDetailCreateRequest memberInfoCreateRequest, @RequestPart(value = "file") MultipartFile multipartFile){
        memberDetailCreateUseCase.createMemberDetail(memberInfoCreateRequest, multipartFile);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/register")
    public ApplicationResponse<CheckRegisterResponse> checkMemberRegistered(@RequestParam String email, @RequestParam String password) {
         CheckRegisterResponse response = memberCheckUseCase.checkMemberRegistered(email, password);
         return ApplicationResponse.ok(response);
    }

    @GetMapping("/info")
    public ApplicationResponse<MemberDetailInfoResponse> getMemberDetailInfo() {
        MemberDetailInfoResponse response = memberGetUseCase.getMemberDetailInfo();
        return ApplicationResponse.ok(response);
    }

    @PostMapping("/verify")
    public ApplicationResponse<Void> postVerifyEmail(@RequestParam String email) {
        emailVerificationUseCase.sendVerificationEmail(email);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/verify/code")
    public ApplicationResponse<VerifyCodeResponse> verifyCode(@RequestParam String email, @RequestParam String code) {
        VerifyCodeResponse response = emailVerificationUseCase.verifyCode(email, code);
        return ApplicationResponse.ok(response);
    }

    @DeleteMapping
    public ApplicationResponse<Void> deleteMember(){
        memberDeleteUseCase.deleteMember();
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/password")
    public ApplicationResponse<CheckPasswordResponse> checkPassword(@RequestParam String password) {
        CheckPasswordResponse response = memberCheckUseCase.checkPassword(password);
        return ApplicationResponse.ok(response);
    }

    @PutMapping("/password")
    public ApplicationResponse<Void> putPassword(@RequestBody MemberPasswordChangeRequest memberPasswordChangeRequest) {
        memberPasswordChangeUseCase.changePassword(memberPasswordChangeRequest);
        return ApplicationResponse.ok(null);
    }
}
