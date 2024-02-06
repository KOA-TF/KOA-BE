package com.koa.coremodule.report.domain.entity;

import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private String content;
    private Boolean isHided = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void hide() { this.isHided = true; }

    public boolean isHided() { return this.isHided; }

}
