package com.koa.coremodule.notice.domain.entity;

import com.koa.coremodule.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class NoticeView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_view_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ViewType view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public static NoticeView create(ViewType view, Member member, Notice notice) {
        return NoticeView.builder()
                .view(view)
                .member(member)
                .notice(notice)
                .build();
    }

}
