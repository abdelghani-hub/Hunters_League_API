package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.Competition;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {
    @Transactional
    Competition save(@Valid Competition competition);

    Optional<Competition> findById(UUID id);

    @Transactional
    Competition update(Competition competition);

    boolean delete(UUID id);

    Optional<Competition> findByCode(String code);
}
