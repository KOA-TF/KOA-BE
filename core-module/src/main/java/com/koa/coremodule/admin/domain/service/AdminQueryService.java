package com.koa.coremodule.admin.domain.service;

import com.koa.coremodule.admin.domain.entity.Admin;
import com.koa.coremodule.admin.domain.repository.AdminRepository;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminQueryService {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    public Long checkPassword(Integer password) {

        Admin admin = adminRepository.findAdminByPassword(password);
        return admin.getId();
    }

    public Long saveMember(Member member) {

        memberRepository.save(member);
        Optional<Member> memberResult = memberRepository.findByEmail(member.getEmail());

        if (memberResult.isPresent()) {
            return memberResult.get().getId();
        } else {
            return 0L;
        }
    }
}
