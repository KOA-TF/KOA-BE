package com.koa.coremodule.fcm.domain.service;

import com.koa.commonmodule.exception.BusinessException;
import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.repository.AlarmRepository;
import com.koa.coremodule.fcm.domain.repository.AlarmViewRepository;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmQueryService {

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final AlarmRepository alarmRepository;
    private final AlarmViewRepository alarmViewRepository;

    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public Alarm findAlarmById(Long alarmId) {
        return alarmRepository.findById(alarmId)
                .orElseThrow(() -> new BusinessException(Error.ALARM_NOT_FOUND));
    }

    public List<AlarmView> findViews(Long memberId) {
        return alarmViewRepository.findAllByMemberId(memberId);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(Error.MEMBER_NOT_FOUND));
    }

    public Member findNoticeMember(Long noticeId) {
        Long memberId = noticeRepository.findMemberIdByNoticeId(noticeId);
        return findMember(memberId);
    }

    public Boolean existsByAlarmIdAndMemberId(Long alarmId, Long memberId) {

        return alarmRepository.existsByAlarmIdAndMemberId(alarmId, memberId);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

}
