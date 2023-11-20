package com.koa.coremodule.vote.application.mapper;

import com.koa.coremodule.notice.application.dto.CurriculumListResponse;
import com.koa.coremodule.notice.domain.entity.Notice;
import com.koa.coremodule.vote.application.dto.VoteItemRequest;
import com.koa.coremodule.vote.domain.entity.Vote;
import com.koa.coremodule.vote.domain.entity.VoteItem;
import com.koa.coremodule.vote.domain.entity.VoteItemRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = Vote.class
)
public interface VoteMapper {

    @Mappings({
            @Mapping(source = "title", target = "title")
    })
    Vote toVoteEntity(String title);

    @Mappings({
            @Mapping(source = "voteItemName", target = "name")
    })
    VoteItem toVoteItemEntity(String name);

    @Mappings({
            @Mapping(source = "memberId", target = "memberId"),
            @Mapping(source = "voteItemId", target = "voteItemId"),
    })
    VoteItemRecord toVoteRecordEntity(VoteItemRequest voteItemRequest);


}
