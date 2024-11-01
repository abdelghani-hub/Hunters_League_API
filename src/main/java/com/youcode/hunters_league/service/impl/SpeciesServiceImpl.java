package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.repository.SpeciesRepository;
import com.youcode.hunters_league.service.SpeciesService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    public SpeciesServiceImpl(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Override
    public Species create(@Valid Species species) {
        // check if species already exists
        if (speciesRepository.existsByName(species.getName())) {
            throw new AlreadyExistException("name", "Species already exists");
        }
        return speciesRepository.save(species);
    }

    @Override
    @Transactional
    public boolean delete(UUID id) {
        if (speciesRepository.existsById(id)) {
            // delete
            speciesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}