package com.koa.apimodule.command.api;

import com.koa.coremodule.auth.application.common.consts.AuthConsts;
import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.service.AuthUseCase;
import com.koa.coremodule.auth.application.service.LogoutUseCase;
import com.koa.coremodule.member.domain.entity.Authority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {


    private final AuthUseCase authUseCase;
    private final LogoutUseCase logoutUseCase;

    @GetMapping("/login/{authority}")
    public AuthResponse authLogin(@PathVariable Authority authority, @RequestParam String email, @RequestParam String password){
        return authUseCase.authLogin(authority, email, password);
    }

    @GetMapping("/reissue")
    public AuthResponse authReissue(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        return authUseCase.reissue(refreshToken);
    }

    @DeleteMapping("/logout")
    public void logout(@RequestHeader(AuthConsts.REFRESH_TOKEN_HEADER) String refreshToken){
        logoutUseCase.logoutAccessUser(refreshToken);
    }
}
