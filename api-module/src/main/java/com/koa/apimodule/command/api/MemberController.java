package com.koa.apimodule.command.api;

import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.request.MemberPasswordChangeRequest;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
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
    public void postMemberDetail(@RequestPart(value = "dto") MemberDetailCreateRequest memberInfoCreateRequest, @RequestPart(value = "file") MultipartFile multipartFile){
        memberDetailCreateUseCase.createMemberDetail(memberInfoCreateRequest, multipartFile);
    }

    @PostMapping("/register")
    public void checkMemberRegistered(@RequestParam String email, @RequestParam String password) {
         memberCheckUseCase.checkMemberRegistered(email, password);
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

    @PostMapping("/password")
    public void checkPassword(@RequestParam String password) {
        memberCheckUseCase.checkPassword(password);
    }

    @PutMapping("/password")
    public void putPassword(@RequestBody MemberPasswordChangeRequest memberPasswordChangeRequest) {
        memberPasswordChangeUseCase.changePassword(memberPasswordChangeRequest);
    }
}
