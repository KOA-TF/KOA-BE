package com.koa.coremodule.notice.application.dto.fcm;

public record SendCommentNotificationRequest(String content, Long commentId) {

}
