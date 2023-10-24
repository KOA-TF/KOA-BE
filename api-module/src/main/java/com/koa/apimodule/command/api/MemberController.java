package com.koa.apimodule.command.api;

import com.koa.coremodule.member.application.dto.MemberInfo;
import com.koa.coremodule.member.application.service.MemberInfoGetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberInfoGetUseCase memberInfoGetUseCase;

    @GetMapping()
    public MemberInfo getMemberInfo(){
        return memberInfoGetUseCase.getMemberInfo();
    }
}
