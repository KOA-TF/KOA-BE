package com.koa.coremodule.notice.mapper;

import com.koa.coremodule.notice.application.dto.*;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.notice.repository.projection.CurriculumProjection;
import com.koa.coremodule.notice.repository.projection.NoticeDetailProjection;
import com.koa.coremodule.notice.repository.projection.NoticeListProjection;
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
            @Mapping(source = "curriculumName", target = "curriculum"),
            @Mapping(source = "teamName", target = "team"),
            @Mapping(source = "date", target = "date")
    })
    List<NoticeListResponse> toNoticeListDTO(List<NoticeListProjection> company);

    @Mappings({
            @Mapping(source = "curriculumId", target = "curriculumId"),
            @Mapping(source = "curriculumName", target = "curriculumName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "title", target = "title")
    })
    List<CurriculumResponse> toCurriculumDTO(List<CurriculumProjection> company);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "createdAt", target = "date")
    })
    List<CurriculumListResponse> toCurriculumListDTO(List<Notice> company);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content")
    })
    Notice toNoticeEntity(NoticeRequest request);

    @Mappings({
            @Mapping(source = "curriculumName", target = "curriculumName"),
            @Mapping(source = "teamName", target = "teamName"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "imageUrl", target = "imageUrl")
    })
    NoticeDetailResponse toNoticeDetailDTO(NoticeDetailProjection projection);

}
