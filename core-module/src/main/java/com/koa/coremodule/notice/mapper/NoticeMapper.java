package com.koa.coremodule.notice.mapper;

import com.koa.coremodule.notice.application.dto.NoticeListResponse;
import com.koa.coremodule.notice.domain.entity.Notice;
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
            @Mapping(source = "teamName", target = "team")
    })
    List<NoticeListResponse> toNoticeListDTO(List<NoticeListProjection> company);

}
