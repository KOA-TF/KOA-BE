package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.request.MemberDetailUpdateRequest;
import com.koa.coremodule.member.application.dto.request.MemberPasswordChangeRequest;
import com.koa.coremodule.member.application.dto.response.CheckEmailResponse;
import com.koa.coremodule.member.application.dto.response.CheckPasswordResponse;
import com.koa.coremodule.member.application.dto.response.CheckRegisterResponse;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberHomeResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoListResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.dto.response.VerifyCodeResponse;
import com.koa.coremodule.member.application.service.EmailVerificationUseCase;
import com.koa.coremodule.member.application.service.MemberCheckUseCase;
import com.koa.coremodule.member.application.service.MemberDeleteUseCase;
import com.koa.coremodule.member.application.service.MemberDetailChangeUseCase;
import com.koa.coremodule.member.application.service.MemberDetailCreateUseCase;
import com.koa.coremodule.member.application.service.MemberDetailGetUseCase;
import com.koa.coremodule.member.application.service.MemberGetUseCase;
import com.koa.coremodule.member.application.service.MemberPasswordChangeUseCase;
import com.koa.coremodule.member.application.service.MemberRegisterUseCase;
import java.util.List;
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
    private final MemberDetailGetUseCase memberDetailGetUseCase;
    private final MemberDetailCreateUseCase memberDetailCreateUseCase;
    private final MemberDetailChangeUseCase memberDetailChangeUseCase;
    private final MemberCheckUseCase memberCheckUseCase;
    private final MemberDeleteUseCase memberDeleteUseCase;
    private final EmailVerificationUseCase emailVerificationUseCase;
    private final MemberPasswordChangeUseCase memberPasswordChangeUseCase;
    private final MemberRegisterUseCase memberRegisterUseCase;

    @GetMapping("/info")
    public ApplicationResponse<MemberInfoResponse> getMemberInfo(){
        MemberInfoResponse response = memberGetUseCase.getMemberInfo();
        return ApplicationResponse.ok(response);
    }

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

    @PostMapping("/check/register")
    public ApplicationResponse<CheckRegisterResponse> checkMemberRegistered(@RequestParam String email, @RequestParam String password) {
         CheckRegisterResponse response = memberCheckUseCase.checkMemberRegistered(email, password);
         return ApplicationResponse.ok(response);
    }

    @PostMapping("/register")
    public ApplicationResponse<Void> postMemberDetail(@RequestParam String email, @RequestParam String password){
        memberRegisterUseCase.registerMember(email, password);
        return ApplicationResponse.ok(null);
    }


    @GetMapping("/info/detail")
    public ApplicationResponse<MemberDetailInfoResponse> getMemberDetailInfo() {
        MemberDetailInfoResponse response = memberDetailGetUseCase.getMemberDetailInfo();
        return ApplicationResponse.ok(response);
    }

    @PostMapping("/email")
    public ApplicationResponse<CheckEmailResponse> checkEmail(@RequestParam String email) {
        CheckEmailResponse response = memberCheckUseCase.checkEmail(email);
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

    @PutMapping("/password/unauthenticated")
    public ApplicationResponse<Void> putPasswordUnauthenticated(@RequestParam String email, @RequestBody MemberPasswordChangeRequest memberPasswordChangeRequest) {
        memberPasswordChangeUseCase.changePasswordUnauthenticated(email, memberPasswordChangeRequest);
        return ApplicationResponse.ok(null);
    }

    @GetMapping("/info/home")
    public ApplicationResponse<MemberHomeResponse> getMemberInfoHome() {
        MemberHomeResponse response = memberGetUseCase.getMemberHomeInfo();
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/info/list")
    public ApplicationResponse<List<MemberInfoListResponse>> getMemberInfoList() {
        List<MemberInfoListResponse> response = memberDetailGetUseCase.getMemberInfoList();
        return ApplicationResponse.ok(response);
    }
}
