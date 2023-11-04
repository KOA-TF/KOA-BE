package com.koa.coremodule.auth.domain.repository;

import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndTokenType(String email, TokenType tokenType);
    void deleteByValue(String value);
}
