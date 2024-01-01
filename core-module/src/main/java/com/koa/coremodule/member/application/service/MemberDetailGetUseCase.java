package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.member.application.dto.response.InterestInfoResponse;
import com.koa.coremodule.member.application.dto.response.LinkInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoListResponse;
import com.koa.coremodule.member.application.dto.response.MemberSearchResponse;
import com.koa.coremodule.member.application.mapper.MemberDetailMapper;
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
public class MemberDetailGetUseCase {

    private final MemberUtils memberUtils;
    private final MemberDetailQueryService memberDetailQueryService;
    private final InterestQueryService interestQueryService;
    private final LinkQueryService linkQueryService;


    public MemberDetailInfoResponse getMemberDetailInfo(){
        final Member member = memberUtils.getAccessMember();
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(member.getId());
        final List<InterestInfoResponse> interestInfoResponses = getInterestInfoResponses(memberDetail);
        final List<LinkInfoResponse> linkInfoResponses = getLinkInfoResponses(memberDetail);
        return MemberDetailMapper.mapToMemberDetailInfoResponse(memberDetail, interestInfoResponses, linkInfoResponses);
    }

    public List<MemberInfoListResponse> getMemberInfoList(){
        final List<MemberDetail> memberDetails = memberDetailQueryService.findAllMemberDetailSorted();
        final List<MemberInfoListResponse> memberInfoListResponses = memberDetails.stream()
                .map(MemberDetailMapper::mapToMemberInfoListResponse)
                .toList();
        return memberInfoListResponses;
    }

    public MemberSearchResponse getMemberSearchInfo(Long memberId){
        final MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(memberId);
        final List<InterestInfoResponse> interestInfoResponses = getInterestInfoResponses(memberDetail);
        final List<LinkInfoResponse> linkInfoResponses = getLinkInfoResponses(memberDetail);
        return MemberDetailMapper.mapToMemberSearchResponse(memberDetail, interestInfoResponses, linkInfoResponses);
    }

    private List<InterestInfoResponse> getInterestInfoResponses(MemberDetail memberDetail){
        final List<Interest> interests = interestQueryService.findInterestsByMemberDetailId(memberDetail.getId());
        return interests.stream()
                .map(MemberDetailMapper::mapToInterestInfoResponse)
                .toList();
    }

    private List<LinkInfoResponse> getLinkInfoResponses(MemberDetail memberDetail){
        final List<Link> links =  linkQueryService.findLinksByMemberDetailId(memberDetail.getId());
        return links.stream()
                .map(MemberDetailMapper::mapToLinkInfoResponse)
                .toList();
    }
}