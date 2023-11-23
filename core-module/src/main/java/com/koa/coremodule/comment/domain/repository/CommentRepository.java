package com.koa.coremodule.comment.domain.repository;

import com.koa.coremodule.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


	@Query("select c from Comment c join fetch c.writer where c.notice.id = :noticeId and c.parent is null order by c.createdAt asc")
	List<Comment> findByNoticeId(@Param("noticeId") Long noticeId);

	@Query("select c from Comment c join fetch c.writer where c.notice.id = :noticeId and c.parent is not null order by c.createdAt asc")
	List<Comment> findChildByNoticeId(@Param("noticeId") Long noticeId);

	@Query("select c from Comment c where c.parent.id = :commentId order by c.createdAt asc")
	List<Comment> findChildByCommentId(@Param("commentId") Long commentId);
}
