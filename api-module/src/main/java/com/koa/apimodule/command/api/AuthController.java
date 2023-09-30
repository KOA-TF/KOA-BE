package com.koa.apimodule.command.api;

import com.koa.coremodule.auth.application.dto.AuthResponse;
import com.koa.coremodule.auth.application.service.AuthUseCase;
import com.koa.coremodule.auth.domain.jwt.JWTProvider;
import com.koa.coremodule.member.domain.entity.Authority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthUseCase authUseCase;
    private final JWTProvider jwtProvider;

    @GetMapping("/login/{authority}")
    public AuthResponse authLogin(@PathVariable Authority authority, @RequestParam String email, @RequestParam String password){
        return authUseCase.authLogin(authority, email, password);
    }

}
