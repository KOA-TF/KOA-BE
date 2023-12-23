package com.koa.coremodule.fcm.domain.entity;

import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.notice.domain.entity.ViewType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class AlarmView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_view_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ViewType view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

}
