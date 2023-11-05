package com.koa.coremodule.notice.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeDeleteService {

    private final NoticeRepository noticeRepository;

    public void deleteNoticeByNoticeIds(List<Long> noticeIds) {
        noticeRepository.deleteByNoticeIds(noticeIds);
    }
}