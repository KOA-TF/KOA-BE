package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.MemberDetail;
import com.koa.coremodule.member.domain.exception.MemberDetailNotFoundException;
import com.koa.coremodule.member.domain.exception.MemberError;
import com.koa.coremodule.member.domain.repository.MemberDetailRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailQueryService {
    private final MemberDetailRepository memberDetailRepository;

    public MemberDetail findMemberDetailByMemberId(Long memberId) {
        return memberDetailRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberDetailNotFoundException(MemberError.MEMBER_DEAITL_NOT_FOUND));
    }

    public List<MemberDetail> findMemberDetailListByMemberIdList(List<Long> memberIdList){
        return memberDetailRepository.findAllByMemberIdIn(memberIdList);
    }

    public boolean isMemberDetailExist(Long memberId){
        return memberDetailRepository.existsByMemberId(memberId);
    }

    public List<MemberDetail> findAllMemberDetailSorted(){
        return memberDetailRepository.findAllSortedByPartAndName();
    }
}
