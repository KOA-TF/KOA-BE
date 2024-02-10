package com.koa.coremodule.comment.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.exception.CommentError;
import com.koa.coremodule.comment.domain.exception.CommentNotFoundException;
import com.koa.coremodule.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(CommentError.COMMENT_NOT_FOUND));
    }

    public List<Comment> getCommentByNoticeId(Long noticeId) {
        return commentRepository.findByNoticeId(noticeId);
    }

    public List<Comment> getChildCommentByCommentId(Long commentId) {
        return commentRepository.findChildByCommentId(commentId);
    }

    public Map<Long, List<Comment>> getChildCommentByNoticeId(Long noticeId){
        final List<Comment> commentList = commentRepository.findChildByNoticeId(noticeId);
        Map<Long, List<Comment>> commentMap =
                commentList.stream()
                        .collect(Collectors.groupingBy(Comment::getParentId));
        return commentMap;
    }

}