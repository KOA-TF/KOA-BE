package com.koa.coremodule.notice.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.member.domain.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Setter
@SQLDelete(sql = "UPDATE notice SET is_deleted = true WHERE notice_id = ?")
@Where(clause = "is_deleted = false")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;
    private String content;
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_team_id")
    private NoticeTeam noticeTeam;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "notice_view_id")
    private NoticeView noticeView;

    @Builder
    public Notice(String title, String content, Member member, Curriculum curriculum) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.curriculum = curriculum;
    }

    public void settingInfo(Member member, NoticeTeam team, Curriculum curriculum, Notice notice) {
        this.noticeTeam = team;
        this.curriculum = curriculum;
        this.noticeView = NoticeView.create(ViewType.NONE, member, notice);
        this.member = member;
    }

    public void update(String title, String content, NoticeTeam team, Curriculum curriculum) {
        this.title = title;
        this.content = content;
        this.noticeTeam = team;
        this.curriculum = curriculum;
    }
}
