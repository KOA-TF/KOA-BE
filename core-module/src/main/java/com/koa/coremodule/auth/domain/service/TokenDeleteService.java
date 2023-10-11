package com.koa.coremodule.auth.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.auth.domain.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TokenDeleteService {
    private final TokenRepository tokenRepository;
    public void deleteToken(final String email){
        tokenRepository.deleteByEmail(email);
    }
}
