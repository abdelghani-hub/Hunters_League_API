package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.exception.NullOrBlankArgException;
import com.youcode.hunters_league.service.CompetitionService;
import com.youcode.hunters_league.web.vm.competition.CompetitionEditVM;
import com.youcode.hunters_league.web.vm.competition.CompetitionVM;
import com.youcode.hunters_league.web.vm.mapper.CompetitionEditVmMapper;
import com.youcode.hunters_league.web.vm.mapper.CompetitionVmMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;
    private final CompetitionVmMapper competitionVmMapper;
    private final CompetitionEditVmMapper competitionEditVmMapper;

    public CompetitionController(CompetitionService competitionService, CompetitionVmMapper competitionVmMapper, CompetitionEditVmMapper competitionEditVmMapper) {
        this.competitionService = competitionService;
        this.competitionVmMapper = competitionVmMapper;
        this.competitionEditVmMapper = competitionEditVmMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<CompetitionVM> create(@RequestBody @Valid CompetitionVM competitionVM) {
        Competition competition = competitionVmMapper.toCompetition(competitionVM);
        Competition savedCompetition = competitionService.save(competition);
        CompetitionVM savedCompetitionVM = competitionVmMapper.toCompetitionVM(savedCompetition);
        return new ResponseEntity<>(savedCompetitionVM, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CompetitionEditVM> update(@RequestBody @Valid CompetitionEditVM competitionEditVM) {
        Competition competition = competitionEditVmMapper.toCompetition(competitionEditVM);
        Competition updatedCompetition = competitionService.update(competition);
        CompetitionEditVM updatedCompetitionEditVM = competitionEditVmMapper.toCompetitionEditVM(updatedCompetition);
        return new ResponseEntity<>(updatedCompetitionEditVM, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(String id) throws NullOrBlankArgException {
        if (id == null || id.isEmpty())
            throw new NullOrBlankArgException("id");

        Map<String, String> res = new HashMap<>();
        if (competitionService.delete(UUID.fromString(id))){
            res.put("message", "Competition deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        res.put("error", "Competition Not Found!");
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}