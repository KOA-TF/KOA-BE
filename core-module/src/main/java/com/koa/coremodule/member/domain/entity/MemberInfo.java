package com.koa.coremodule.member.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class MemberInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_info_id")
    private Long id;

    private String major;
    private String part;
    private String phoneNumber;
    private String description;

    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member user;
}
