package com.koa.coremodule.notice.application.mapper;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.domain.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeDetailListProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeListProjection;
import com.koa.coremodule.notice.domain.repository.projection.NoticeV2DetailListProjection;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = Notice.class
)
public interface NoticeMapper {

    @Mappings({
            @Mapping(source = "noticeId", target = "noticeId"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "curriculumName", target = "curriculumName"),
            @Mapping(source = "teamName", target = "teamName"),
            @Mapping(source = "date", target = "date")
    })
    List<NoticeListResponse> toNoticeListDTO(List<NoticeListProjection> notice);

    @Mappings({
            @Mapping(source = "curriculumId", target = "curriculumId"),
            @Mapping(source = "curriculumName", target = "curriculumName"),
            @Mapping(source = "title", target = "title")
    })
    List<CurriculumResponse> toCurriculumDTO(List<CurriculumProjection> notice);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content")
    })
    List<CurriculumListResponse> toCurriculumListDTO(List<Notice> notice);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content")
    })
    Notice toNoticeEntity(NoticeRequest request);

    @Mappings({
            @Mapping(source = "noticeTitle", target = "title"),
            @Mapping(source = "content", target = "content")
    })
    Notice toNoticeV2Entity(NoticeV2Request request);

    @Mappings({
            @Mapping(source = "noticeId", target = "noticeId"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content")
    })
    NoticeUpdateComponents toUpdateNoticeEntity(NoticeUpdateRequest request);

    @Mappings({
            @Mapping(source = "noticeId", target = "noticeId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "profileImage", target = "profileImage"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "curriculumName", target = "curriculum"),
            @Mapping(source = "teamName", target = "team"),
            @Mapping(source = "date", target = "date")
    })
    NoticeDetailListResponse toNoticeDetailDTO(NoticeDetailListProjection projection);

    @Mappings({
            @Mapping(source = "noticeId", target = "noticeId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "profileImage", target = "profileImage"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "curriculumName", target = "curriculum"),
            @Mapping(source = "teamName", target = "team"),
            @Mapping(source = "date", target = "date")
    })
    NoticeDetailInfoResponse toNoticeV2DetailDTO(NoticeV2DetailListProjection projection);
}
