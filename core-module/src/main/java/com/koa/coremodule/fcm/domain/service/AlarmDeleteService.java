package com.koa.coremodule.fcm.domain.service;

import com.koa.coremodule.fcm.domain.repository.AlarmRepository;
import com.koa.coremodule.fcm.domain.repository.AlarmViewRepository;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmDeleteService {

    private final AlarmRepository alarmRepository;
    private final AlarmViewRepository alarmViewRepository;
    private final MemberRepository memberRepository;

    public void deleteFcmToken(Long id) {
        memberRepository.deleteAlarmToken(id);
    }

    public void deleteAlarm(Long alarmId) {

        alarmViewRepository.deleteAlarmViewByAlarmId(alarmId);
        alarmRepository.deleteAlarmById(alarmId);
    }

}
