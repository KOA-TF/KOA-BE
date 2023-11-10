package com.koa.apimodule.command.api;

import com.koa.commonmodule.common.ApplicationResponse;
import com.koa.coremodule.comment.application.dto.request.CommentCreateRequest;
import com.koa.coremodule.comment.application.dto.response.CommentInfoResponse;
import com.koa.coremodule.comment.application.dto.response.CommentListResponse;
import com.koa.coremodule.comment.application.service.CommentCreateUseCase;
import com.koa.coremodule.comment.application.service.CommentDeleteUseCase;
import com.koa.coremodule.comment.application.service.CommentGetUseCase;
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
    private final CommentGetUseCase commentGetUseCase;

    @PostMapping("/{noticeId}")
    public ApplicationResponse<CommentInfoResponse> createComment(@PathVariable Long noticeId, @RequestBody CommentCreateRequest commentCreateRequest) {
        CommentInfoResponse response =  commentCreateUseCase.createComment(noticeId, commentCreateRequest);
        return ApplicationResponse.ok(response);
    }

    @PostMapping("/{noticeId}/{commentId}")
    public ApplicationResponse<CommentInfoResponse> createReComment(@PathVariable Long noticeId, @PathVariable Long commentId,
                                               @RequestBody CommentCreateRequest commentCreateRequest) {
        CommentInfoResponse response = commentCreateUseCase.createReComment(noticeId, commentId, commentCreateRequest);
        return ApplicationResponse.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ApplicationResponse<Void> delete(@PathVariable Long commentId){
        commentDeleteUseCase.deleteComment(commentId);
        return ApplicationResponse.ok(null);
    }

    @GetMapping("/{noticeId}")
    public ApplicationResponse<List<CommentListResponse>> getCommentList(@PathVariable Long noticeId){
        List<CommentListResponse> response = commentGetUseCase.getCommentList(noticeId);
        return ApplicationResponse.ok(response);
    }

    @GetMapping("/child/{commentId}")
    public ApplicationResponse<List<CommentInfoResponse>> getChildCommentList(@PathVariable Long commentId){
        List<CommentInfoResponse> response = commentGetUseCase.getReCommentList(commentId);
        return ApplicationResponse.ok(response);
    }

}
