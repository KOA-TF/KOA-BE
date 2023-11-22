package com.koa.coremodule.notice.application.service;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.application.dto.NoticeRequest;
import com.koa.coremodule.notice.application.dto.NoticeUpdateRequest;
import com.koa.coremodule.notice.application.dto.NoticeViewResponse;
import com.koa.coremodule.notice.application.mapper.NoticeMapper;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.exception.NoticeNotFoundException;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import com.koa.coremodule.notice.domain.service.NoticeDeleteService;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        String imageUrl = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            imageUrl = awsS3Service.uploadFile(multipartFile);
        }

        // 공지 본문 저장
        Notice noticeEntity = noticeMapper.toNoticeEntity(request);
        final Member member = noticeQueryService.findMemberById(request.getMemberId()).orElseThrow(() -> new NoticeNotFoundException(Error.MEMBER_NOT_FOUND));
        Notice savedNotice = noticeQueryService.save(noticeEntity);

        // 공지 저장 시 연관 테이블 모두 맵핑
        final NoticeTeam noticeTeam = noticeQueryService.findNoticeTeamById(request.getTeamId()).orElseThrow(() -> new EntityNotFoundException("팀을 찾을 수 없습니다."));
        final Curriculum curriculum = noticeQueryService.findCurriculumById(request.getCurriculumId()).orElseThrow(() -> new EntityNotFoundException("커리큘럼을 찾을 수 없습니다."));
        noticeEntity.settingInfo(imageUrl, member, noticeTeam, curriculum, savedNotice);

        noticeQueryService.save(noticeEntity);
        return savedNotice.getId();
    }

    public Long updateNotice(NoticeUpdateRequest request, MultipartFile multipartFile) {
        final Notice findNotice = noticeQueryService.findByNoticeId(request.getNoticeId());
        final String imageUrl = findNotice.getNoticeImage().getImageUrl();

        //이미지 업로드 || 업데이트
        updateImage(multipartFile, imageUrl, findNotice);

        // 공지 본문 수정
        final NoticeTeam noticeTeam = noticeQueryService.findNoticeTeamById(request.getTeamId()).get();
        final Curriculum curriculum = noticeQueryService.findCurriculumById(request.getCurriculumId()).get();
        findNotice.update(request.getTitle(), request.getContent(), noticeTeam, curriculum);
        return findNotice.getId();
    }

    private void updateImage(MultipartFile multipartFile, String imageUrl, Notice findNotice) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            //이미지 삭제
            if (imageUrl != null && !imageUrl.isEmpty() && !imageUrl.trim().isEmpty()) {
                awsS3Service.deleteFile(imageUrl.replace(S3_URL, ""));
            } else {
                final String uploadUrl = awsS3Service.uploadFile(multipartFile);
                findNotice.getNoticeImage().setImageUrl(uploadUrl);
            }
        }
    }

    public void deleteNotice(Long noticeId) {

        String imageUrl = noticeQueryService.findImageByNoticeId(noticeId);

        // 이미지가 존재하는 경우 S3 이미지 삭제
        if (imageUrl != null) {
            awsS3Service.deleteFile(imageUrl.replace(S3_URL, ""));
            noticeDeleteService.deleteImageByeNoticeId(noticeId);
        }

        // 공지 본문 삭제
        noticeDeleteService.deleteNoticeBySingleNoticeId(noticeId);
    }

    public NoticeListResponse selectNoticeDetail(Long memberId, Long noticeId) {

        NoticeListProjection projection = noticeQueryService.selectNoticeDetail(noticeId);
        NoticeListResponse response = noticeMapper.toNoticeDetailDTO(projection);

        // 조회 여부 기록 업데이트
        ViewType viewResponse = noticeQueryService.findSingleViewType(noticeId, memberId);
        Long viewId = noticeQueryService.findSingleViewId(noticeId, memberId);

        if (viewResponse.equals(ViewType.NONE)) {
            noticeQueryService.updateSingleViewYn(viewId, memberId, ViewType.VIEWED);
        }

        return response;
    }

}
