package com.koa.coremodule.auth.application.handler.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlarmTokenDeleteEvent {
    private final Long memberId;
}
