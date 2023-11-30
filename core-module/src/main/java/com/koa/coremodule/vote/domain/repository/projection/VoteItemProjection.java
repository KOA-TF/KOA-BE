package com.koa.coremodule.vote.domain.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.koa.coremodule.vote.domain.entity.QVoteItem.voteItem;
import static com.koa.coremodule.vote.domain.entity.QVoteItemRecord.voteItemRecord;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteItemProjection {

    public static final ConstructorExpression<VoteItemProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(VoteItemProjection.class,
                    voteItem.voteItemName,
                    voteItemRecord.id.count()
            );

    private String item;
    private Long count;

}
