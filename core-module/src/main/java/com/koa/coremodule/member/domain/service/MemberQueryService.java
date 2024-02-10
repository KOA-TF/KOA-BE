package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.commonmodule.exception.BusinessException;
import com.koa.coremodule.member.domain.entity.Authority;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.exception.MemberError;
import com.koa.coremodule.member.domain.exception.MemberNotFoundException;
import com.koa.coremodule.member.domain.repository.MemberRepository;

import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public void checkExecutiveAccountExist(Authority authority, String email, String password) {
        BooleanSupplier existenceCheckFunction = getExistenceCheckFunction(authority, email, password);
        if (!existenceCheckFunction.getAsBoolean()) {
            throw new MemberNotFoundException(MemberError.MEMBER_NOT_FOUND);
        }
    }

    public void checkMemberAccountExist(String email, String password) {
        if (!memberRepository.existsByEmailAndPassword(email, password)) {
            throw new MemberNotFoundException(MemberError.MEMBER_NOT_FOUND);
        }
    }

    public Member findMemberByEmailAndPassword(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new MemberNotFoundException(MemberError.MEMBER_NOT_FOUND));
    }

    public boolean checkMemberExist(String email, String password) {
        return memberRepository.existsByEmailAndPassword(email, password);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(MemberError.MEMBER_NOT_FOUND));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(MemberError.MEMBER_NOT_FOUND));
    }

    public boolean checkEmailExist(String email) {
        return memberRepository.existsByEmail(email);
    }

    public List<Member> findAllMemberByIds(List<Long> memberIds) {
        return memberRepository.findAllById(memberIds);
    }

    private BooleanSupplier getExistenceCheckFunction(Authority authority, String email, String password) {
        return () -> (authority == Authority.MEMBER)
                ? memberRepository.existsByEmailAndPassword(email, password)
                : memberRepository.existsByAuthorityAndEmailAndPassword(authority, email, password);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

}
