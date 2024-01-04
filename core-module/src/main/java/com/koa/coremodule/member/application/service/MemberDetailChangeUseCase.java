package com.koa.coremodule.member.application.service;

import com.koa.commonmodule.annotation.ApplicationService;
import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.member.application.dto.request.MemberDetailUpdateRequest;
import com.koa.coremodule.member.application.handler.event.InterestUpdateEvent;
import com.koa.coremodule.member.application.handler.event.LinkUpdateEvent;
import com.koa.coremodule.member.application.mapper.MemberDetailMapper;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.entity.Link;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.service.MemberDetailQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import java.util.List;
import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class MemberDetailChangeUseCase {
    private final MemberUtils memberUtils;
    private final MemberDetailQueryService memberDetailQueryService;
    private final AwsS3Service awsS3Service;
    private final ApplicationEventPublisher applicationEventPublisher;


    public void  updateMemberDetail(MemberDetailUpdateRequest memberDetailUpdateRequest, MultipartFile multipartFile){
        String imageUrl = awsS3Service.uploadFile(multipartFile);
        Member member = memberUtils.getAccessMember();
        member.updatePhoneNumber(memberDetailUpdateRequest.getPhoneNumber());
        MemberDetail memberDetail = memberDetailQueryService.findMemberDetailByMemberId(member.getId());
        memberDetail.updateMemberDetail(memberDetailUpdateRequest.getMajor(), memberDetailUpdateRequest.getPart(),
                memberDetailUpdateRequest.getDescription(), imageUrl);
        List<Interest> interests = createNewInterest(memberDetailUpdateRequest, memberDetail);
        List<Link> links = createNewLink(memberDetailUpdateRequest, memberDetail);
        applicationEventPublisher.publishEvent(new InterestUpdateEvent(memberDetail.getId(), interests));
        applicationEventPublisher.publishEvent(new LinkUpdateEvent(memberDetail.getId(), links));

    }

    private List<Interest> createNewInterest(MemberDetailUpdateRequest memberDetailUpdateRequest, MemberDetail memberDetail) {
        return createNewList(memberDetailUpdateRequest.getInterests(), MemberDetailMapper::mapToInterest, memberDetail);
    }

    private List<Link> createNewLink(MemberDetailUpdateRequest memberDetailUpdateRequest, MemberDetail memberDetail) {
        return createNewList(memberDetailUpdateRequest.getLinks(), MemberDetailMapper::mapToLink, memberDetail);
    }

    private <T, R> List<R> createNewList(List<T> requestList, BiFunction<T, MemberDetail, R> mapperFunction, MemberDetail memberDetail) {
        if (requestList != null) {
            return requestList.stream()
                    .map(item -> mapperFunction.apply(item, memberDetail))
                    .toList();
        }
        return List.copyOf(List.of());
    }

}
