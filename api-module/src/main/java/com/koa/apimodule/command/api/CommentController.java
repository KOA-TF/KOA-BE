package com.koa.apimodule.command.api;

import com.koa.coremodule.comment.application.dto.request.CommentCreateRequest;
import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.application.dto.response.CommentListResponse;
import com.koa.coremodule.comment.application.service.CommentCreateUseCase;
import com.koa.coremodule.comment.application.service.CommentDeleteUseCase;
import com.koa.coremodule.comment.application.service.CommentReadUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentController {

    private final CommentCreateUseCase commentCreateUseCase;
    private final CommentDeleteUseCase commentDeleteUseCase;
    private final CommentReadUseCase commentReadUseCase;

    @PostMapping("/{noticeId}")
    public CommentInfoResponse createComment(@PathVariable Long noticeId, @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createComment(noticeId, commentCreateRequest);
    }

    @PostMapping("/{noticeId}/{commentId}")
    public CommentInfoResponse createReComment(@PathVariable Long noticeId, @PathVariable Long commentId,
                                               @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentCreateUseCase.createReComment(noticeId, commentId, commentCreateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentDeleteUseCase.deleteComment(commentId);
    }

    @GetMapping("/{noticeId}")
    public List<CommentListResponse> getCommentList(@PathVariable Long noticeId){
        return commentReadUseCase.getCommentList(noticeId);
    }

    @GetMapping("/child/{commentId}")
    public List<CommentInfoResponse> getChildCommentList(@PathVariable Long commentId){
        return commentReadUseCase.getReCommentList(commentId);
    }

}
