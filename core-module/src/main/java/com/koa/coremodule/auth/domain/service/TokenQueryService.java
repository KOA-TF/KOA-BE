package com.koa.coremodule.auth.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.exception.NotExistTokenException;
import com.koa.coremodule.auth.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TokenQueryService {

    private final TokenRepository tokenRepository;

    public String findEmailByValue(final String value, final TokenType tokenType) {
        Token token = findToken(value, tokenType);
        return token.getEmail();
    }

    public Token findTokenByValue(String value, TokenType tokenType) {
        return findToken(value, tokenType);
    }

    private Token findToken(final String value, final TokenType tokenType){
        return tokenRepository.findByValueAndTokenType(value, tokenType)
                .orElseThrow(() -> new NotExistTokenException(Error.NOT_EXIST_TOKEN));
    }
}
