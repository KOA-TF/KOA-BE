package com.koa.coremodule.member.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.commonmodule.utils.DomainFieldUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@SQLDelete(sql = "UPDATE member_detail SET is_deleted = true WHERE member_detail_id = ?")
@Where(clause = "is_deleted = false")
public class MemberDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_detail_id")
    private Long id;

    private String major;
    @Enumerated(EnumType.STRING)
    private Part part;
    private String description;
    private String profileImage;
    private Boolean isDeleted = Boolean.FALSE;

    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberDetail(String major, Part part, String description, String profileImage, Member member) {
        this.major = major;
        this.part = part;
        this.description = description;
        this.profileImage = profileImage;
        this.member = member;
    }

    public void updateMemberDetail(String major, String part, String description, String profileImage) {
        this.major = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(major)
            .currentValue(this.major)
            .validate();
        this.part = Part.valueOf(DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(part)
            .currentValue(this.part.name())
            .validate());
        this.description = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(description)
            .currentValue(this.description)
            .validate();
        this.profileImage = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(profileImage)
            .currentValue(this.profileImage)
            .validate();
    }

}
