package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.notice.application.dto.NoticeDetailResponse;
import com.koa.coremodule.notice.application.dto.NoticeRequest;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.application.mapper.NoticeMapper;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeSaveUseCase {

    private final NoticeQueryService noticeQueryService;
    private final NoticeMapper noticeMapper;
    private final AwsS3Service awsS3Service;

    public Long saveNotice(NoticeRequest request, MultipartFile multipartFile) {

        // image 저장
        if (multipartFile == null) {
            request.setImageUrl(null);
        } else {
            String url = awsS3Service.uploadFile(multipartFile);
            request.setImageUrl(url);
        }

        // 공지 본문 저장
        Notice noticeEntity = noticeMapper.toNoticeEntity(request);
        Notice savedNotice = noticeQueryService.save(noticeEntity);
        return savedNotice.getId();
    }

    public NoticeDetailResponse selectNoticeDetail(Long noticeId) {

        NoticeDetailProjection projection = noticeQueryService.selectNoticeDetail(noticeId);
        NoticeDetailResponse response = noticeMapper.toNoticeDetailDTO(projection);

        return response;
    }

}
