package com.koa.coremodule.auth.application.handler;

import com.koa.coremodule.auth.application.handler.event.UnusedTokenExpireEvent;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.auth.domain.service.TokenQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class UnusedTokenExpireEventHandler {

    private final TokenQueryService tokenQueryService;
    private final TokenDeleteService tokenDeleteService;

    @EventListener
    public void handle(UnusedTokenExpireEvent event){
        final List<Token> allToken = tokenQueryService.findAllByEmailAndTokenType(event.getEmail(), event.getTokenType());
        tokenDeleteService.deleteAllToken(allToken);
    }

}
