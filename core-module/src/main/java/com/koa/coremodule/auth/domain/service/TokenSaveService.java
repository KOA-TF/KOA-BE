package com.koa.coremodule.auth.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TokenSaveService {
    private final TokenRepository tokenRepository;
    public void saveToken( final String token, final String email, final TokenType tokenType){
        tokenRepository.save(Token.builder()
                .tokenType(tokenType)
                .value(token)
                .email(email)
                .build());
    }
}
