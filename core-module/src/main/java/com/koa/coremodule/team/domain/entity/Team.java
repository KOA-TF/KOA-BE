package com.koa.coremodule.team.domain.entity;

import com.koa.commonmodule.domain.BaseEntity;
import com.koa.commonmodule.utils.DomainFieldUtils;
import com.koa.coremodule.curriculum.domain.entity.Curriculum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Builder
    public Team(String teamName, Curriculum curriculum) {
        this.teamName = teamName;
        this.curriculum = curriculum;
    }

    public void updateTeamName(String teamName) {
        this.teamName = DomainFieldUtils.DomainValidateBuilder.builder(String.class)
            .newValue(teamName)
            .currentValue(this.teamName)
            .validate();
    }
}
