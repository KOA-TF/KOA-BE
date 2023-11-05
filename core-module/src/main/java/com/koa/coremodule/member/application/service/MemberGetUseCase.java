package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.InterestInfoResponse;
import com.koa.coremodule.member.application.dto.response.LinkInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.application.mapper.MemberMapper;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.InterestQueryService;
import com.koa.coremodule.member.domain.service.LinkQueryService;
import com.koa.coremodule.member.domain.service.MemberDetailQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberGetUseCase {

    private final MemberUtils memberUtils;
    private final MemberDetailQueryService memberDetailQueryService;
    private final InterestQueryService interestQueryService;
    private final LinkQueryService linkQueryService;

    public MemberInfoResponse getMemberInfo(){
        final Member member = memberUtils.getAccessMember();
        boolean isMemberDetailExist = memberDetailQueryService.isMemberDetailExist(member.getId());
        return MemberMapper.mapToMemberInfoResponse(member, isMemberDetailExist);
    }

    public MemberDetailInfoResponse getMemberDetailInfo(){
        final Member member = memberUtils.getAccessMember();
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDatailByMemberId(member.getId());
        final List<Interest> interests = interestQueryService.findInterestsByMemberDetailId(memberDetail.getId());
        final List<InterestInfoResponse> interestInfoResponses = interests.stream()
                .map(MemberMapper::mapToInterestInfoResponse)
                .toList();
        final List<Link> links =  linkQueryService.findLinksByMemberDetailId(memberDetail.getId());
        final List<LinkInfoResponse> linkInfoResponses = links.stream()
                .map(MemberMapper::mapToLinkInfoResponse)
                .toList();
        return MemberMapper.mapToMemberDetailInfoResponse(memberDetail, interestInfoResponses, linkInfoResponses);
    }
}
