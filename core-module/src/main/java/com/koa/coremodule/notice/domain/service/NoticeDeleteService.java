package com.koa.coremodule.notice.domain.service;

import com.koa.commonmodule.annotation.DomainService;
import com.koa.coremodule.notice.domain.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeDeleteService {

    private final NoticeRepository noticeRepository;

    public void deleteNoticeByNoticeIds(List<Long> noticeIds) {
        noticeRepository.deleteByNoticeIds(noticeIds);
    }

    public void deleteImageByeNoticeId(Long noticeId) {
        noticeRepository.deleteImageByNoticeId(noticeId);
    }

    public void deleteNoticeBySingleNoticeId(Long noticeId) {
        noticeRepository.deleteByNoticeId(noticeId);
    }
}
