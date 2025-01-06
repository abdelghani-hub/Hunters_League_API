package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.domain.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipationService {
    Page<Participation> findAll(Pageable pageable);
}
