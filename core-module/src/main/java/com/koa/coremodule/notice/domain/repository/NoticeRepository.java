package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.Notice;
import java.util.List;

import com.koa.coremodule.notice.domain.entity.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeDynamicRepository {

    @Query("select n.id from Notice n where n.member.id = :memberId")
    List<Long> findIdsByMemberId(Long memberId);

    @Query("select NoticeImage from NoticeImage n where n.notice.id = :noticeId")
    NoticeImage findImageByNoticeId(Long noticeId);

    @Modifying
    @Query("update Notice n set n.isDeleted=true where n.id in (:noticeIds)")
    void deleteByNoticeIds(List<Long> noticeIds);

    @Modifying
    @Query("update NoticeImage n set n.isDeleted=true where n.notice.id = :noticeId")
    void deleteImageByNoticeId(Long noticeId);

    @Modifying
    @Query("update Notice n set n.isDeleted=true where n.id = :noticeId")
    void deleteByNoticeId(Long noticeId);

}
