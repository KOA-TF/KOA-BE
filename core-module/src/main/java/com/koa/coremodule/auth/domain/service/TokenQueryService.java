package com.koa.coremodule.auth.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.exception.AuthError;
import com.koa.coremodule.auth.domain.exception.NotExistTokenException;
import com.koa.coremodule.auth.domain.repository.TokenRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenQueryService {

    private final TokenRepository tokenRepository;

    public String findEmailByValue(final String value, final TokenType tokenType) {
        Token token = findTokenByvalueAndTokenType(value, tokenType);
        return token.getEmail();
    }

    public Token findTokenByTokenValue(String value, TokenType tokenType) {
        Token token = findTokenByvalueAndTokenType(value, tokenType);
        return token;
    }

    public Token findTokenByvalueAndTokenType(String value, TokenType tokenType) {
        Token token = tokenRepository.findByTokenValueAndTokenType(value, tokenType)
                .orElseThrow(() -> new NotExistTokenException(AuthError.NOT_EXIST_TOKEN));
        return token;
    }

    public List<Token> findAllByEmailAndTokenType(String email, TokenType tokenType){
        return tokenRepository.findAllByEmailAndTokenType(email, tokenType);
    }
}
