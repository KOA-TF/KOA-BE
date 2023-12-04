package com.koa.coremodule.auth.domain.repository;

import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenValueAndTokenType(String value, TokenType tokenType);
    void deleteByTokenValue(String value);
    List<Token> findAllByEmailAndTokenType(String email, TokenType tokenType);
}
