package com.koa.coremodule.curriculum.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import com.koa.coremodule.curriculum.domain.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class CurriculumSaveService {

        private final CurriculumRepository curriculumRepository;

        public void saveCurriculum(Curriculum curriculum) {
            curriculumRepository.save(curriculum);
        }
}
