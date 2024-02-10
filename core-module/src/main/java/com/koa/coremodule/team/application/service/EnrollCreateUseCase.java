package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.service.EnrollQueryService;
import com.koa.coremodule.team.domain.service.EnrollSaveService;
import com.koa.coremodule.team.domain.service.EnrollValidateService;
import com.koa.coremodule.team.domain.service.TeamQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class EnrollCreateUseCase {

    private final EnrollSaveService enrollSaveService;
    private final EnrollQueryService enrollQueryService;
    private final MemberQueryService memberQueryService;
    private final EnrollValidateService enrollValidateService;
    private final TeamQueryService teamQueryService;

    public void createEnroll(Long teamId, Long memberId) {
        Team team = teamQueryService.findTeamById(teamId);
        Member member = memberQueryService.findMemberById(memberId);
        List<Long> memberIds = enrollQueryService.findMemberIdListByTeamId(teamId);
        enrollValidateService.validateEnroll(memberIds, member.getId());
        enrollSaveService.saveEnroll(new Enroll(team, member));
    }

}
