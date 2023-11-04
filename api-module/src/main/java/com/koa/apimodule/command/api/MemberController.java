package com.koa.apimodule.command.api;

import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.dto.response.RegisterResponse;
import com.koa.coremodule.member.application.service.EmailVerificationUseCase;
import com.koa.coremodule.member.application.service.MemberDeleteUseCase;
import com.koa.coremodule.member.application.service.MemberDetailCreateUseCase;
import com.koa.coremodule.member.application.service.MemberGetUseCase;
import com.koa.coremodule.member.application.service.MemberRegisterGetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final MemberRegisterGetUseCase memberRegisterGetUseCase;
    private final MemberDeleteUseCase memberDeleteUseCase;
    private final EmailVerificationUseCase emailVerificationUseCase;

    @GetMapping
    public MemberInfoResponse getMemberInfo(){
        return memberGetUseCase.getMemberInfo();
    }

    @PostMapping
    public void postMemberInfo(@RequestPart(value = "dto") MemberDetailCreateRequest memberInfoCreateRequest, @RequestPart(value = "file") MultipartFile multipartFile){
        memberDetailCreateUseCase.createMemberInfo(memberInfoCreateRequest, multipartFile);
    }

    @PostMapping("/register")
    public RegisterResponse authJoin(@RequestParam String email, @RequestParam String password) {
        return memberRegisterGetUseCase.getMemberRegistered(email, password);
    }

    @GetMapping("/info")
    public MemberDetailInfoResponse getMemberDetailInfo() {
        return memberGetUseCase.getMemberDetailInfo();
    }

    @PostMapping("/verify")
    public void postVerifyEmail(@RequestParam String email) {
        emailVerificationUseCase.sendVerificationEmail(email);
    }

    @PostMapping("/verify/code")
    public void verifyCode(@RequestParam String email, @RequestParam String code) {
        emailVerificationUseCase.verifyCode(email, code);
    }

    @DeleteMapping
    public void deleteMember(){
        memberDeleteUseCase.deleteMember();
    }
}
