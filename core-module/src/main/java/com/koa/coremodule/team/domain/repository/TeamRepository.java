package com.koa.coremodule.team.domain.repository;

import com.koa.coremodule.team.domain.entity.Team;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByCurriculumId(Long curriculumId);

    Optional<Team> findById(Long teamId);

}
