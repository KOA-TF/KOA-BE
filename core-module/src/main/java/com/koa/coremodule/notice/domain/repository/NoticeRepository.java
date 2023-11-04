package com.koa.coremodule.notice.domain.repository;

import com.koa.coremodule.notice.domain.entity.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeDynamicRepository {

    @Query("select n.id from Notice n where n.member.id = :memberId")
    List<Long> findIdsByMemberId(Long memberId);

    @Modifying
    @Query("update Notice n set n.isDeleted=true where n.id in (:noticeIds)")
    void deleteByNoticeIds(List<Long> noticeIds);
}
