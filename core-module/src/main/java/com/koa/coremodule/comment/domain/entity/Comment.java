package com.koa.coremodule.comment.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.member.domain.entity.Member;
import jakarta.persistence.*;
import java.time.format.DateTimeFormatter;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    private Boolean isAnonymous;

    public Long getParentId() {
        return this.parent != null ? this.parent.getId() : null;
    }

    public String getCreatedAtByFormat() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
