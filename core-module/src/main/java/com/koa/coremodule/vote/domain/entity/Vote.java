package com.koa.coremodule.vote.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.coremodule.notice.domain.entity.Notice;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    private String voteTitle;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VoteItem> voteItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

}
