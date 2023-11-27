package com.koa.coremodule.vote.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class VoteItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

    private String voteItemName;

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
