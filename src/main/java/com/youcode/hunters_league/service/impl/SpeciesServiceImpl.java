package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.repository.SpeciesRepository;
import com.youcode.hunters_league.service.SpeciesService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;

    private JdbcTemplate jdbcTemplate;

    public SpeciesServiceImpl(SpeciesRepository speciesRepository, JdbcTemplate jdbcTemplate) {
        this.speciesRepository = speciesRepository;
        this.jdbcTemplate = jdbcTemplate;
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
            // First bulk update all related hunt records
            jdbcTemplate.update(
                    "UPDATE hunt SET species_id = NULL WHERE species_id = ?",
                    id
            );
            // delete
            speciesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Species update(Species species) {
        // check if species exists
        if (speciesRepository.existsById(species.getId())) {
            // Check if species name already exists
            if (speciesRepository.existsByNameAndIdNot(species.getName(), species.getId())) {
                throw new AlreadyExistException("name", species.getName());
            }
            return speciesRepository.save(species);
        }
        return null;
    }

    @Override
    public Page<Species> findAll(Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }
}