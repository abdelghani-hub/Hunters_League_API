package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.service.SpeciesService;
import com.youcode.hunters_league.web.vm.mapper.SpeciesVmMapper;
import com.youcode.hunters_league.web.vm.species.SpeciesVM;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final SpeciesVmMapper speciesVmMapper;

    public SpeciesController(SpeciesService speciesService, SpeciesVmMapper speciesVmMapper) {
        this.speciesService = speciesService;
        this.speciesVmMapper = speciesVmMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<SpeciesVM> create(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = speciesVmMapper.toSpecies(speciesVM);
        Species savedSpecies = speciesService.create(species);
        SpeciesVM savedSpeciesVM = speciesVmMapper.toSpeciesVM(savedSpecies);
        return new ResponseEntity<>(savedSpeciesVM, HttpStatus.CREATED);
    }
}