package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.notice.application.dto.NoticeDetailResponse;
import com.koa.coremodule.notice.application.dto.NoticeRequest;
import com.koa.coremodule.notice.domain.entity.NoticeImage;
import com.koa.coremodule.notice.domain.service.NoticeDeleteService;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.application.mapper.NoticeMapper;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeSaveUseCase {

    @Value("${cloud.aws.s3.url}")
    private String S3_URL;
    private final NoticeQueryService noticeQueryService;
    private final NoticeDeleteService noticeDeleteService;
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

    public Long updateNotice(NoticeRequest request, MultipartFile multipartFile) {

        // 티켓 메모의 이미지를 수정하는 경우
        String url = "";
        if (!(multipartFile == null)) {
            // 기존에 이미지가 존재했다면
            if (request.getImageUrl() != null) {
                // 해당 이미지 삭제
                awsS3Service.deleteFile(request.getImageUrl().replace(S3_URL, ""));
            }
            url = awsS3Service.uploadFile(multipartFile);
            request.setImageUrl(url);
        } else {
            if (request.getImageUrl() != null) {
                url = request.getImageUrl();
                request.setImageUrl(url);
            }
        }

        // 공지 본문 수정
        Notice noticeEntity = noticeMapper.toNoticeEntity(request);
        Notice updatedNotice = noticeQueryService.save(noticeEntity);
        return updatedNotice.getId();
    }

    public void deleteNotice(Long noticeId) {

        Optional<NoticeImage> entityResponse = Optional.ofNullable(noticeQueryService.findImageByNoticeId(noticeId));
        NoticeImage noticeImageResponse = entityResponse.get();

        // 이미지가 존재하는 경우 S3 이미지 삭제
        if (noticeImageResponse.getImageUrl() != null) {
            awsS3Service.deleteFile(noticeImageResponse.getImageUrl().replace(S3_URL, ""));
            noticeDeleteService.deleteImageByeNoticeId(noticeId);
        }

        // 공지 본문 삭제
        noticeDeleteService.deleteNoticeBySingleNoticeId(noticeId);
    }

    public NoticeDetailResponse selectNoticeDetail(Long noticeId) {

        NoticeDetailProjection projection = noticeQueryService.selectNoticeDetail(noticeId);
        NoticeDetailResponse response = noticeMapper.toNoticeDetailDTO(projection);

        return response;
    }

}
