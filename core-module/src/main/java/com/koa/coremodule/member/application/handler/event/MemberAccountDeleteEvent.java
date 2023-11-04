package com.koa.coremodule.member.application.handler.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberAccountDeleteEvent {
    private final Long memberId;
}
