package com.koa.coremodule.member.application.mapper;

import com.koa.coremodule.member.application.dto.request.InterestCreateRequest;
import com.koa.coremodule.member.application.dto.request.LinkCreateRequest;
import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.dto.response.InterestInfoResponse;
import com.koa.coremodule.member.application.dto.response.LinkInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberDetailInfoResponse;
import com.koa.coremodule.member.application.dto.response.MemberInfoResponse;
import com.koa.coremodule.member.domain.entity.Category;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.entity.Part;
import com.koa.coremodule.member.domain.entity.Type;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static MemberInfoResponse mapToMemberInfoResponse(Member member, boolean isMemberDetailExist){
        return MemberInfoResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .isMemberDetailExist(isMemberDetailExist)
                .build();
    }

    public static MemberDetail mapToMemberInfo(Member member, MemberDetailCreateRequest memberInfoCreateRequest, String imageUrl){
        return MemberDetail.builder()
                .major(memberInfoCreateRequest.getMajor())
                .part(Part.valueOf(memberInfoCreateRequest.getPart()))
                .phoneNumber(memberInfoCreateRequest.getPhoneNumber())
                .description(memberInfoCreateRequest.getDescription())
                .profileImage(imageUrl)
                .member(member)
                .build();
    }

    public static Interest mapToInterest(InterestCreateRequest interestCreateRequest, MemberDetail memberDetail){
        return Interest.builder()
                .category(Category.valueOf(interestCreateRequest.getCategory()))
                .content(interestCreateRequest.getContent())
                .memberDetail(memberDetail)
                .build();
    }

    public static Link mapToLink(LinkCreateRequest linkCreateRequest, MemberDetail memberDetail){
        return Link.builder()
                .type(Type.valueOf(linkCreateRequest.getType()))
                .link(linkCreateRequest.getLink())
                .memberDetail(memberDetail)
                .build();
    }

    public static MemberDetailInfoResponse mapToMemberDetailInfoResponse(MemberDetail memberDetail, List<InterestInfoResponse> interests, List<LinkInfoResponse> links){
        return MemberDetailInfoResponse.builder()
                .major(memberDetail.getMajor())
                .part(memberDetail.getPart().toString())
                .interests(interests)
                .phoneNumber(memberDetail.getPhoneNumber())
                .description(memberDetail.getDescription())
                .links(links)
                .build();
    }

    public static InterestInfoResponse mapToInterestInfoResponse(Interest interest){
        return InterestInfoResponse.builder()
                .category(interest.getCategory().toString())
                .content(interest.getContent())
                .build();
    }

    public static LinkInfoResponse mapToLinkInfoResponse(Link link){
        return LinkInfoResponse.builder()
                .type(link.getType().toString())
                .link(link.getLink())
                .build();
    }
}
