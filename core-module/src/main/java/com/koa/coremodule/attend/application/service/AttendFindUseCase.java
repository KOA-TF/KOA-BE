package com.koa.coremodule.attend.application.service;

import com.koa.coremodule.attend.application.dto.AttendContent;
import com.koa.coremodule.attend.application.dto.AttendList;
import com.koa.coremodule.attend.application.mapper.AttendListMapper;
import com.koa.coremodule.attend.domain.entity.AttendStatus;
import com.koa.coremodule.attend.domain.repository.projection.AttendListProjection;
import com.koa.coremodule.attend.domain.service.AttendQueryService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendFindUseCase {

    @Value("${cloud.aws.s3.url}")
    private String QR_TEXT;

    private final MemberUtils memberUtils;
    private final AttendQueryService attendQueryService;

    public AttendContent getAttendStatus() {

        Member memberRequest = memberUtils.getAccessMember();
        int penalty;
        String passYn;

        Map<AttendStatus, Long> attendStatusMap = attendQueryService.getAttendStatus(memberRequest.getId());
        int present = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.PRESENT, 0L));
        int absent = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.ABSENT, 0L));
        int late = Math.toIntExact(attendStatusMap.getOrDefault(AttendStatus.LATE, 0L));

        penalty = absent * 2 + late;
        if (penalty <= 5) {
            passYn = LateStatus.PASS.getMessage();
        } else {
            passYn = LateStatus.FAIL.getMessage();
        }

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

    public Boolean checkText(String text) {

        String correctText = QR_TEXT;
        return correctText.equals(text);
    }

}
