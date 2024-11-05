package com.youcode.hunters_league.repository;

import com.youcode.hunters_league.domain.Species;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    boolean existsByName(String name);

    Optional<Species> findByName(String value);

    boolean existsByNameAndIdNot(String name, UUID id);
}
