package com.koa.coremodule.member.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.commonmodule.utils.DomainFieldUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


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
    private Boolean isRegistered = Boolean.FALSE;

    @Builder
    public Member(String name, String period, String email, String phoneNumber, String password, Authority authority) {
        this.name = name;
        this.period = period;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.authority = authority;
    }

    public void updateFcmToken(String fcmToken) {
        if(Objects.nonNull(fcmToken)) {
            this.fcmToken = fcmToken;
        }
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void register() {
        this.isRegistered = true;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(phoneNumber)
            .currentValue(this.phoneNumber)
            .validate();
    }

    public void updatePassword(String password) {
        this.password = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(password)
            .currentValue(this.password)
            .validate();
    }
}
