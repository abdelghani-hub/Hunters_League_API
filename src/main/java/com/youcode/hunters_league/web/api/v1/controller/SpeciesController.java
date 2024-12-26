package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.exception.NullOrBlankArgException;
import com.youcode.hunters_league.service.SpeciesService;
import com.youcode.hunters_league.web.vm.mapper.SpeciesEditVmMapper;
import com.youcode.hunters_league.web.vm.mapper.SpeciesVmMapper;
import com.youcode.hunters_league.web.vm.species.SpeciesEditVM;
import com.youcode.hunters_league.web.vm.species.SpeciesVM;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/species")
@PreAuthorize("hasRole('ADMIN')")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final SpeciesVmMapper speciesVmMapper;
    private final SpeciesEditVmMapper speciesEditVmMapper;

    public SpeciesController(SpeciesService speciesService, SpeciesVmMapper speciesVmMapper, SpeciesEditVmMapper speciesEditVmMapper) {
        this.speciesService = speciesService;
        this.speciesVmMapper = speciesVmMapper;
        this.speciesEditVmMapper = speciesEditVmMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<SpeciesVM> create(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = speciesVmMapper.toSpecies(speciesVM);
        Species savedSpecies = speciesService.create(species);
        SpeciesVM savedSpeciesVM = speciesVmMapper.toSpeciesVM(savedSpecies);
        return new ResponseEntity<>(savedSpeciesVM, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SpeciesEditVM> update(@RequestBody @Valid SpeciesEditVM speciesEditVM) {
        Species species = speciesEditVmMapper.toSpecies(speciesEditVM);
        Species updatedSpecies = speciesService.update(species);
        SpeciesEditVM updatedSpeciesEditVM = speciesEditVmMapper.toSpeciesEditVM(updatedSpecies);
        return new ResponseEntity<>(updatedSpeciesEditVM, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(String id) throws NullOrBlankArgException {
        if (id == null || id.isEmpty())
            throw new NullOrBlankArgException("id");

        Map<String, String> res = new HashMap<>();
        if (speciesService.delete(UUID.fromString(id))){
            res.put("message", "Species deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        res.put("error", "Species not found");
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    // Paginate Species
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('MEMBER')")
    public ResponseEntity<Page<SpeciesVM>> findAll(Pageable pageable){
        Page<Species> farms = speciesService.findAll(pageable);
        Page<SpeciesVM> farmResponseVM = farms.map(speciesVmMapper::toSpeciesVM);
        return new ResponseEntity<>(farmResponseVM , HttpStatus.OK);
    }
}