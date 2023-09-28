package com.koa.coremodule.auth.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Token extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private String email;
    private String tokenValue;

    public static Token createToken(TokenType type, String email, String value){
        return new Token(null, type, email, value);
    }
}
