package com.koa.coremodule.comment.domain.service;


import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentDeleteService {

    private final CommentRepository commentRepository;

    public void deleteComment(Comment comment) {

        commentRepository.deleteById(comment.getId());
    }

}
