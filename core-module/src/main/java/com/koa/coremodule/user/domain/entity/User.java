package com.koa.coremodule.user.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String profileImageUrl;
    private String userId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;
}
