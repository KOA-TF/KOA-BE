package com.koa.coremodule.notice.application.service;

import com.koa.coremodule.image.service.AwsS3Service;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.notice.application.dto.NoticeDetailListResponse;
import com.koa.coremodule.notice.application.dto.NoticeRequest;
import com.koa.coremodule.notice.application.dto.NoticeUpdateRequest;
import com.koa.coremodule.notice.application.dto.NoticeV2Request;
import com.koa.coremodule.notice.application.mapper.NoticeMapper;
import com.koa.coremodule.notice.domain.entity.Curriculum;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.entity.NoticeTeam;
import com.koa.coremodule.notice.domain.entity.ViewType;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailListProjection;
import com.koa.coremodule.notice.domain.service.NoticeDeleteService;
import com.koa.coremodule.notice.domain.service.NoticeQueryService;
import com.koa.coremodule.vote.application.mapper.VoteMapper;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.service.VoteSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
    private final VoteSaveService voteSaveService;
    private final VoteMapper voteMapper;

    public Long saveNotice(NoticeRequest request, MultipartFile multipartFile) {
        // image 저장
        String imageUrl = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            imageUrl = awsS3Service.uploadFile(multipartFile);
        }

        // 공지 본문 저장
        Notice noticeEntity = noticeMapper.toNoticeEntity(request);
        final Member member = noticeQueryService.findMemberById(request.getMemberId());
        Notice savedNotice = noticeQueryService.save(noticeEntity);

        // 공지 저장 시 연관 테이블 모두 맵핑
        final NoticeTeam noticeTeam = noticeQueryService.findNoticeTeamById(request.getTeamId());
        final Curriculum curriculum = noticeQueryService.findCurriculumById(request.getCurriculumId());
        noticeEntity.settingInfo(imageUrl, member, noticeTeam, curriculum, savedNotice);

        noticeQueryService.save(noticeEntity);

        return savedNotice.getId();
    }

    public List<Long> saveNoticeV2(NoticeV2Request request, List<MultipartFile> multipartFiles) {

        List<Long> savedNoticeIds = new ArrayList<>();

        // Iterate through each multipart file
        for (MultipartFile multipartFile : multipartFiles) {
            // image 저장
            String imageUrl = null;
            if (multipartFile != null && !multipartFile.isEmpty()) {
                imageUrl = awsS3Service.uploadFile(multipartFile);
            }

            // 공지 본문 저장
            Notice noticeEntity = noticeMapper.toNoticeV2Entity(request);
            final Member member = noticeQueryService.findMemberById(request.getMemberId());
            Notice savedNotice = noticeQueryService.save(noticeEntity);

            // 공지 저장 시 연관 테이블 모두 맵핑
            final NoticeTeam noticeTeam = noticeQueryService.findNoticeTeamById(request.getTeamId());
            final Curriculum curriculum = noticeQueryService.findCurriculumById(request.getCurriculumId());
            noticeEntity.settingInfo(imageUrl, member, noticeTeam, curriculum, savedNotice);

            noticeQueryService.save(noticeEntity);

            // 투표 제목 저장
            Vote voteEntity = voteMapper.toVoteEntity(request.getVoteTitle());

            //notice 조회
            Notice notice = noticeQueryService.findByNoticeId(savedNotice.getId());

            // Vote 엔티티 생성
            Vote vote = Vote.builder()
                    .voteTitle(voteEntity.getVoteTitle())
                    .notice(notice)
                    .build();

            // Vote 엔티티 저장
            Vote savedVote = voteSaveService.saveVote(vote);

            // 투표 항목 개수 체크 후 각자 저장
            List<String> titles = request.getItem();

            for (String title : titles) {
                VoteItem voteItemEntity = VoteItem.builder()
                        .voteItemName(title)
                        .vote(savedVote) // Vote ID 설정
                        .build();
                voteSaveService.saveVoteItem(voteItemEntity);
            }

            savedNoticeIds.add(savedNotice.getId());
        }

        return savedNoticeIds;
    }

    public Long updateNotice(NoticeUpdateRequest request, MultipartFile multipartFile) {
        final Notice findNotice = noticeQueryService.findByNoticeId(request.getNoticeId());
        final String imageUrl = findNotice.getNoticeImage().getImageUrl();

        //이미지 업로드 || 업데이트
        updateImage(multipartFile, imageUrl, findNotice);

        // 공지 본문 수정
        final NoticeTeam noticeTeam = noticeQueryService.findNoticeTeamById(request.getTeamId());
        final Curriculum curriculum = noticeQueryService.findCurriculumById(request.getCurriculumId());
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

    public NoticeDetailListResponse selectNoticeDetail(Long memberId, Long noticeId) {

        NoticeDetailListProjection projection = noticeQueryService.selectNoticeDetail(noticeId);
        NoticeDetailListResponse response = noticeMapper.toNoticeDetailDTO(projection);

        // 조회 여부 기록 업데이트
        ViewType viewResponse = noticeQueryService.findSingleViewType(noticeId, memberId);
        Long viewId = noticeQueryService.findSingleViewId(noticeId, memberId);

        if (viewResponse.equals(ViewType.NONE)) {
            noticeQueryService.updateSingleViewYn(viewId, memberId, ViewType.VIEWED);
        }

        return response;
    }

}
