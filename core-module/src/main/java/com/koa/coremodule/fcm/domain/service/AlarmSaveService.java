package com.koa.coremodule.fcm.domain.service;

import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.repository.AlarmRepository;
import com.koa.coremodule.fcm.domain.repository.AlarmViewRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmSaveService {

    private final AlarmRepository alarmRepository;
    private final AlarmViewRepository alarmViewRepository;
    private final NoticeRepository noticeRepository;

    public Alarm save(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public void saveAlarmView(AlarmView alarmView) {
        alarmViewRepository.save(alarmView);
    }

    public void deleteFcmToken(Long id) {
        noticeRepository.deleteToken(id);
    }

    public void deleteAlarm(Long alarmId) {

        alarmViewRepository.deleteAlarmViewByAlarmId(alarmId);
        alarmRepository.deleteAlarmById(alarmId);
    }

}
