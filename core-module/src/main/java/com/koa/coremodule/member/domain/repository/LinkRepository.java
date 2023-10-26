package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.Link;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("select l from Link l where l.memberDetail.id = :memberDetailId")
    List<Link> findAllByMemberDetailId(Long memberDetailId);
}
