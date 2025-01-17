package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.Species;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.UUID;

public interface SpeciesService {
    Species create(@Valid Species species);

    @Transactional
    boolean delete(UUID id);

    @Transactional
    Species update(Species species);
}
