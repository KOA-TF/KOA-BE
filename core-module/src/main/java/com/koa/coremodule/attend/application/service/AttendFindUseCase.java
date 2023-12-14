package com.koa.coremodule.attend.application.service;

import com.koa.coremodule.attend.application.dto.AttendContent;
import com.koa.coremodule.attend.application.dto.AttendList;
import com.koa.coremodule.attend.application.dto.AttendSaveRequest;
import com.koa.coremodule.attend.application.mapper.AttendListMapper;
import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;
import com.koa.coremodule.attend.domain.service.AttendQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import com.koa.coremodule.notice.application.service.NoticeFindUseCase;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendFindUseCase {

    @Value("${qr.text}")
    private String QR_TEXT;

    private final MemberUtils memberUtils;
    private final AttendQueryService attendQueryService;
    private final NoticeQueryService noticeQueryService;

    public AttendContent getAttendStatus() {

        Member memberRequest = memberUtils.getAccessMember();

        Map<AttendStatus, Long> attendStatusMap = attendQueryService.getAttendStatus(memberRequest.getId());
        int present = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.PRESENT, 0L));
        int absent = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.ABSENT, 0L));
        int late = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.LATE, 0L));

        int penalty = (absent * 2) + late;
        String passYn = LateStatus.calculatePassYn(penalty);

        AttendContent attendContent = AttendContent.builder()
                .penalty(penalty)
                .present(present)
                .absent(absent)
                .late(late)
                .passYn(passYn)
                .build();
        return attendContent;
    }

    public List<AttendList> getAttendList() {

        Member memberRequest = memberUtils.getAccessMember();
        List<AttendListProjection> projectionList = attendQueryService.getAttendList(memberRequest.getId());
        return projectionList.stream().map(AttendListMapper::toResponse).toList();
    }

    public Boolean checkText(AttendSaveRequest attendSaveRequest) {

        Curriculum curriculum = noticeQueryService.findCurriculumById(attendSaveRequest.curriculumId());

        String correctText = QR_TEXT + curriculum.getCurriculumName();
        return correctText.equals(attendSaveRequest.text());
    }

}
