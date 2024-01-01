package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.member.application.dto.request.MemberDetailCreateRequest;
import com.koa.coremodule.member.application.mapper.MemberDetailMapper;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.InterestSaveService;
import com.koa.coremodule.member.domain.service.LinkSaveService;
import com.koa.coremodule.member.domain.service.MemberDetailSaveService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class MemberDetailCreateUseCase {

    private final MemberUtils memberUtils;
    private final MemberDetailSaveService memberDetailSaveService;
    private final InterestSaveService interestSaveService;
    private final LinkSaveService linkSaveService;
    private final AwsS3Service awsS3Service;

    public void createMemberDetail(MemberDetailCreateRequest memberDetailCreateRequest, MultipartFile multipartFile) {
        String imageUrl = awsS3Service.uploadFile(multipartFile);
        Member member = memberUtils.getAccessMember();
        MemberDetail memberDetail = MemberDetailMapper.mapToMemberDetail(member, memberDetailCreateRequest, imageUrl);
        memberDetailSaveService.saveMemberInfoEntity(memberDetail);
        memberDetailCreateRequest.getInterests().forEach(interestRequest -> {
            Interest interest = MemberDetailMapper.mapToInterest(interestRequest, memberDetail);
            interestSaveService.saveInterestEntity(interest);
        });
        memberDetailCreateRequest.getLinks().forEach(linkRequest -> {
            Link link = MemberDetailMapper.mapToLink(linkRequest, memberDetail);
            linkSaveService.saveLinkEntity(link);
        });
    }
}
