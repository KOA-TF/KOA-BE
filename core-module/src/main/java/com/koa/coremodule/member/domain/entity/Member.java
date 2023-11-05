package com.koa.coremodule.member.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String profileImageUrl;
    private String password;
    private String fcmToken;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void updateFcmToken(String fcmToken) {
        if(Objects.nonNull(fcmToken)) {
            this.fcmToken = fcmToken;
        }
    }
}
