package com.koa.coremodule.comment.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.comment.domain.entity.Comment;
import com.koa.coremodule.comment.domain.exception.CommentError;
import com.koa.coremodule.comment.domain.exception.NotSameUserException;

@DomainService
public class CommentValidateService {

    public void validateSameUser(Comment comment, Long userId){
        if(!comment.getWriter().getId().equals(userId)){
            throw new NotSameUserException(CommentError.NOT_SAME_USER);
        }
    }
}
