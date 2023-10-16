package com.koa.coremodule.report.mapper;

import com.koa.coremodule.report.domain.entity.Report;
import com.koa.coremodule.report.application.dto.ReportRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = Report.class
)
public interface ReportMapper {

    @Mappings({
            @Mapping(source = "content", target = "content")
    })
    Report toReportEntity(ReportRequest request);

}
