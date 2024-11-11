package com.youcode.hunters_league.repository;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

}
