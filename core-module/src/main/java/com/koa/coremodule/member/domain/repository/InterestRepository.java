package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.Interest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    @Query("select i from Interest i where i.memberDetail.id = :memberDetailId")
    List<Interest> findAllByMemberDetailId(Long memberDetailId);

    void deleteByMemberDetailId(Long memberDetailId);
}
