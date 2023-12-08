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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendFindUseCase {

    private final MemberUtils memberUtils;
    private final AttendQueryService attendQueryService;

    private final static String PASS = "수료 가능한 점수에요";
    private final static String FAIL = "수료 불가능한 점수에요.";

    public AttendContent getAttendStatus() {

        Member memberRequest = memberUtils.getAccessMember();
        int penalty;
        String passYn;

        Map<AttendStatus, Integer> attendStatusMap = attendQueryService.getAttendStatus(memberRequest.getId());
        int present = attendStatusMap.getOrDefault(AttendStatus.PRESENT, 0);
        int absent = attendStatusMap.getOrDefault(AttendStatus.ABSENT, 0);
        int late = attendStatusMap.getOrDefault(AttendStatus.LATE, 0);

        penalty = absent * 2 + late;
        if (penalty <= 5) {
            passYn = PASS;
        } else {
            passYn = FAIL;
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

        String correctText = "https://www.kusitmskoa.link";
        return correctText.equals(text);
    }

}
