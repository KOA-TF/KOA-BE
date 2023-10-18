package com.koa.coremodule.comment.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CommentSaveService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {

        commentRepository.save(comment);
    }
}
