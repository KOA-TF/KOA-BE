package com.koa.coremodule.auth.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE token SET is_deleted = true WHERE token_id = ?")
@Where(clause = "is_deleted = false")
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

    private Boolean isDeleted = Boolean.FALSE;

    @Builder
    public Token(TokenType tokenType, String email, String value) {
        this.tokenType = tokenType;
        this.email = email;
        this.tokenValue = value;
    }
}
