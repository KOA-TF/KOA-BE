package com.koa.coremodule.fcm.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.fcm.application.dto.AlarmLists;
import com.koa.coremodule.fcm.domain.entity.Alarm;
import com.koa.coremodule.fcm.domain.entity.AlarmView;
import com.koa.coremodule.fcm.domain.service.AlarmQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmGetUseCase {

    private final AlarmQueryService alarmQueryService;
    private final MemberUtils memberUtils;
    private static final DateTimeFormatter LAST_DATE_FORMATTER = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
    private static final DateTimeFormatter NOW_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    public List<AlarmLists> getAlarmLists() {

        Member memberRequest = memberUtils.getAccessMember();
        List<Alarm> alarmList = alarmQueryService.findAll();
        List<Alarm> filteredAlarmList = alarmList.stream()
                .filter(alarm -> !alarm.getMember().getId().equals(memberRequest.getId()))
                .sorted(Comparator.comparing(Alarm::getCreatedAt).reversed())
                .toList();

        List<AlarmView> alarmViews = alarmQueryService.findViews(memberRequest.getId());
        List<AlarmLists> result = new ArrayList<>();

        for (Alarm a : filteredAlarmList) {
            boolean isViewed = false;

            // 조회 여부 확인
            for (AlarmView al : alarmViews) {
                if (a.getId().equals(al.getAlarm().getId())) {
                    isViewed = true;
                    break;
                }
            }

            DateTimeFormatter selectedFormatter = LocalDate.now().getYear() == a.getCreatedAt().getYear()
                    ? NOW_DATE_FORMATTER
                    : LAST_DATE_FORMATTER;

            AlarmLists alarmLists = AlarmLists.builder()
                    .alarmId(a.getId())
                    .title(a.getTitle())
                    .content(a.getContent())
                    .date(a.getCreatedAt().format(selectedFormatter))
                    .viewYn(isViewed)  // viewYn을 조회 여부에 따라 설정
                    .build();

            if (a.getNotice() != null) {
                alarmLists.setNoticeId(a.getNotice().getId());
            }
            if (a.getComment() != null) {
                alarmLists.setCommentId(a.getComment().getId());
            }

            result.add(alarmLists);
        }

        return result;
    }
}
