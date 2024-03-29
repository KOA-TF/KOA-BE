package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.ViewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeDynamicRepository {

    @Query("select n.id from Notice n where n.member.id = :memberId")
    List<Long> findIdsByMemberId(Long memberId);

    @Query("select v.imageUrl from NoticeImage v where v.notice.id = :noticeId")
    String findImageByNoticeId(Long noticeId);

    @Query("select v.imageUrl from NoticeImage v where v.notice.id = :noticeId")
    List<String> findImagesByNoticeId(Long noticeId);

    @Modifying
    @Query("update Notice n set n.isDeleted=true where n.id in (:noticeIds)")
    void deleteByNoticeIds(List<Long> noticeIds);

    @Modifying
    @Query("delete from NoticeImage n where n.id = :noticeId")
    void deleteImageByNoticeId(Long noticeId);

    @Modifying
    @Query("update Notice n set n.isDeleted=true where n.id = :noticeId")
    void deleteByNoticeId(Long noticeId);

    @Modifying
    @Query("update Notice n set n.title = :title, n.content = :content where n.id = :noticeId")
    void updateNotice(Long noticeId, String title, String content);

    @Query("select v.id from NoticeView v where v.notice.id = :noticeId and v.member.id = :memberId")
    Long findSingleViewId(Long memberId, Long noticeId);

    @Query("select v.view from NoticeView v where v.notice.id = :noticeId and v.member.id = :memberId")
    ViewType findSingleViewType(Long memberId, Long noticeId);

    @Modifying
    @Query("update NoticeView v set v.view = :viewType where v.member.id = :memberId and v.id = :noticeViewId")
    void updateSingleViewYn(Long noticeViewId, Long memberId, ViewType viewType);

    @Modifying
    @Query("update Member m set m.fcmToken = null where m.id = :id")
    void deleteToken(Long id);

    @Query("select n.member.id from Notice n where n.id = :noticeId")
    Long findMemberIdByNoticeId(Long noticeId);

}
