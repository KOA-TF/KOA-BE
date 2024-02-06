package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.member.application.dto.request.CheckEmailRequest;
import com.koa.coremodule.member.application.dto.request.CheckPasswordRequest;
import com.koa.coremodule.member.application.dto.request.MemberPasswordChangeRequest;
import com.koa.coremodule.member.application.dto.request.MemberRegisterRequest;
import com.koa.coremodule.member.application.dto.response.CheckEmailResponse;
import com.koa.coremodule.member.application.dto.response.CheckPasswordResponse;
import com.koa.coremodule.member.application.dto.response.CheckRegisterResponse;
import com.koa.coremodule.member.application.dto.response.MemberHomeResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.service.MemberCheckUseCase;
import com.koa.coremodule.member.application.service.MemberDeleteUseCase;
import com.koa.coremodule.member.application.service.MemberGetUseCase;
import com.koa.coremodule.member.application.service.MemberPasswordChangeUseCase;
import com.koa.coremodule.member.application.service.MemberRegisterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberGetUseCase memberGetUseCase;
    private final MemberCheckUseCase memberCheckUseCase;
    private final MemberDeleteUseCase memberDeleteUseCase;
    private final MemberPasswordChangeUseCase memberPasswordChangeUseCase;
    private final MemberRegisterUseCase memberRegisterUseCase;

    @GetMapping("/info")
    public ApplicationResponse<MemberInfoResponse> getMemberInfo(){
        MemberInfoResponse response = memberGetUseCase.getMemberInfo();
        return ApplicationResponse.ok(response);
    }

    @PostMapping("/check/register")
    public ApplicationResponse<CheckRegisterResponse> checkMemberRegistered(@RequestBody MemberRegisterRequest memberRegisterRequest) {
         CheckRegisterResponse response = memberCheckUseCase.checkMemberRegistered(memberRegisterRequest);
         return ApplicationResponse.ok(response);
    }

    @PostMapping("/register")
    public ApplicationResponse<Void> getMemberRegister(@RequestBody MemberRegisterRequest memberRegisterRequest){
        memberRegisterUseCase.registerMember(memberRegisterRequest);
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/email")
    public ApplicationResponse<CheckEmailResponse> checkEmail(@RequestBody CheckEmailRequest checkEmailRequest) {
        CheckEmailResponse response = memberCheckUseCase.checkEmail(checkEmailRequest);
        return ApplicationResponse.ok(response);
    }

    @DeleteMapping
    public ApplicationResponse<Void> deleteMember(){
        memberDeleteUseCase.deleteMember();
        return ApplicationResponse.ok(null);
    }

    @PostMapping("/password")
    public ApplicationResponse<CheckPasswordResponse> checkPassword(@RequestBody CheckPasswordRequest checkPasswordRequest) {
        CheckPasswordResponse response = memberCheckUseCase.checkPassword(checkPasswordRequest);
        return ApplicationResponse.ok(response);
    }

    @PutMapping("/password")
    public ApplicationResponse<Void> putPassword(@RequestBody MemberPasswordChangeRequest memberPasswordChangeRequest) {
        memberPasswordChangeUseCase.changePassword(memberPasswordChangeRequest);
        return ApplicationResponse.ok(null);
    }

    @PutMapping("/password/unauthenticated")
    public ApplicationResponse<Void> putPasswordUnauthenticated(@RequestBody MemberPasswordChangeRequest memberPasswordChangeRequest) {
        memberPasswordChangeUseCase.changePasswordUnauthenticated(memberPasswordChangeRequest);
        return ApplicationResponse.ok(null);
    }

    @GetMapping("/info/home")
    public ApplicationResponse<MemberHomeResponse> getMemberInfoHome() {
        MemberHomeResponse response = memberGetUseCase.getMemberHomeInfo();
        return ApplicationResponse.ok(response);
    }
}
