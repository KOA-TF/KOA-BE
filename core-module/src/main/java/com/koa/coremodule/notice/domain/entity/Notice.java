package com.koa.coremodule.notice.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.coremodule.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;
}
