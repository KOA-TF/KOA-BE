package com.koa.coremodule.auth.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.auth.application.handler.event.AlarmTokenDeleteEvent;
import com.koa.coremodule.auth.application.utils.TokenExtractUtils;
import com.koa.coremodule.auth.domain.entity.Token;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.auth.domain.service.TokenQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class LogoutUseCase {
    private final TokenDeleteService tokenDeleteService;
    private final TokenQueryService tokenQueryService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MemberUtils memberUtils;

    public void logoutAccessUser(String refreshTokenHeader) {
        final Member member = memberUtils.getAccessMember();
        final String refreshToken = TokenExtractUtils.extractToken(refreshTokenHeader);
        final Token refreshTokenEntity = tokenQueryService.findTokenByTokenValue(refreshToken, TokenType.REFRESH_TOKEN);
        tokenDeleteService.deleteToken(refreshTokenEntity);
        applicationEventPublisher.publishEvent(new AlarmTokenDeleteEvent(member.getId()));
    }

}
