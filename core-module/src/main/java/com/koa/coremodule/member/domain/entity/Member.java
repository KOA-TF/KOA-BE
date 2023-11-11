package com.koa.coremodule.member.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Objects;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE member SET is_deleted = true WHERE member_id = ?")
@Where(clause = "is_deleted = false")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String period;
    private String email;
    private String phoneNumber;
    private String password;
    private String fcmToken;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private Boolean isDeleted = Boolean.FALSE;

    @Builder
    public Member(String name, String period, String email, String phoneNumber, String password, Authority authority) {
        this.name = name;
        this.period = period;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.authority = authority;
    }

    public void updatePassword(String password) {
        if(this.password.equals(password))
            return;
        this.password = Objects.requireNonNull(password, "password must be not null");
    }

    public void updateFcmToken(String fcmToken) {
        if(Objects.nonNull(fcmToken)) {
            this.fcmToken = fcmToken;
        }
    }
}
