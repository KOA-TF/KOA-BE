package com.koa.coremodule.report.mapper;

import com.koa.coremodule.notice.domain.entity.NoticeReport;
import com.koa.coremodule.report.application.dto.ReportRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = NoticeReport.class
)
public interface ReportMapper {

    @Mappings({
            @Mapping(source = "content", target = "content")
    })
    NoticeReport toReportEntity(ReportRequest request);

}
