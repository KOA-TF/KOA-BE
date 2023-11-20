package com.koa.commonmodule.presentation;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonRestControllerAdvise {

    private final SlackService slackService;

    @ExceptionHandler({RuntimeException.class, BusinessException.class, Exception.class})
    public void commonExceptionHandler(Exception e) {
        slackService.sendingExceptionMessage(e);
    }
}

