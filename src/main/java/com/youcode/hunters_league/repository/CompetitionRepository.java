package com.youcode.hunters_league.repository;

import com.youcode.hunters_league.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    boolean existsByCode(String code);
    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByDateBetween(LocalDateTime prevSunday, LocalDateTime nextSaturday);

    boolean existsByIdNotAndDateBetween(UUID competitionId, LocalDateTime prevSunday, LocalDateTime nextSaturday);
}
