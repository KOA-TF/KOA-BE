package com.koa.coremodule.auth.application.handler;

import com.koa.coremodule.auth.application.handler.event.AlarmTokenDeleteEvent;
import com.koa.coremodule.fcm.domain.service.AlarmDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class AlarmTokenDeleteEventHandler {

    private final AlarmDeleteService alarmDeleteService;

    @EventListener
    public void handle(AlarmTokenDeleteEvent event){
        alarmDeleteService.deleteFcmToken(event.getMemberId());
    }
}
