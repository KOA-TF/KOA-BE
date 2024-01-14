package com.koa.coremodule.team.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.team.application.dto.request.EnrollCreateRequest;
import com.koa.coremodule.team.domain.entity.Enroll;
import com.koa.coremodule.team.domain.entity.Team;
import com.koa.coremodule.team.domain.service.EnrollSaveService;
import com.koa.coremodule.team.domain.service.TeamQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class EnrollCreateUseCase {

    private final EnrollSaveService enrollSaveService;
    private final TeamQueryService teamQueryService;
    private final MemberQueryService memberQueryService;

    public void createEnroll(Long teamId, EnrollCreateRequest enrollCreateRequest) {
        Team team = teamQueryService.findTeamById(teamId);
        List<Member> memberList = memberQueryService.findAllMemberByIds(enrollCreateRequest.memberIds());
        memberList.forEach(member -> enrollSaveService.saveEnroll(new Enroll(team, member)));
    }

}
