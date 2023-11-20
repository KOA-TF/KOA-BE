package com.koa.coremodule.vote.domain.repository;

import com.koa.coremodule.vote.domain.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
}
