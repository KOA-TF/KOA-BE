package com.koa.coremodule.member.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.member.domain.entity.Interest;
import com.koa.coremodule.member.domain.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class InterestSaveService {
    private final InterestRepository interestRepository;

    public void saveInterestEntity(Interest interest){
        interestRepository.save(interest);
    }

    public void saveAll(Iterable<Interest> interests) {
        interestRepository.saveAll(interests);
    }
}
