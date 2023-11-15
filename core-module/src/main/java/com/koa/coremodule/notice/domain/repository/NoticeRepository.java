package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.application.dto.NoticeViewRequest;
import com.koa.coremodule.notice.domain.entity.Notice;
import java.util.List;

import com.koa.coremodule.notice.domain.entity.NoticeImage;
import com.koa.coremodule.notice.domain.entity.ViewType;
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

    @Query("select v.view from NoticeView v where v.notice.id = :noticeId and v.member.id = :memberId")
    ViewType findSingleViewYn(Long memberId, Long noticeId);

    @Modifying
    @Query("update NoticeView v set v.view = :viewType where v.member.id = :memberId and v.notice.id = :noticeId")
    void updateSingleViewYn(Long noticeId, Long memberId, ViewType viewType);

}
