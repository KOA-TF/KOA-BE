package com.koa.coremodule.notice.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE notice_image SET is_deleted = true WHERE notice_image_id = ?")
@Where(clause = "is_deleted = false")
@ToString
@Setter
public class NoticeImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_image_id")
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    private Boolean isDeleted = Boolean.FALSE;

    public static NoticeImage create(String imageUrl) {
        return NoticeImage.builder()
                .isDeleted(false)
                .imageUrl(imageUrl)
                .build();
    }

    public static NoticeImage createNoticeImage(String imageUrl, Notice notice) {
        NoticeImage noticeImage = new NoticeImage();
        noticeImage.setImageUrl(imageUrl);
        noticeImage.setNotice(notice);
        noticeImage.setIsDeleted(false);
        return noticeImage;
    }

}
