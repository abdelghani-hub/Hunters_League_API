package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.Participation;
import com.youcode.hunters_league.service.impl.ParticipationServiceImpl;
import com.youcode.hunters_league.web.vm.mapper.ParticipationVmMapper;
import com.youcode.hunters_league.web.vm.participation.ParticipationCreateVM;
import com.youcode.hunters_league.web.vm.participation.ParticipationVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/participations")
public class ParticipationController {

    private ParticipationServiceImpl participationServiceImpl;
    private final ParticipationVmMapper participationVmMapper;

    public ParticipationController(ParticipationServiceImpl participationServiceImpl,
                                   ParticipationVmMapper participationVmMapper) {
        this.participationServiceImpl = participationServiceImpl;
        this.participationVmMapper = participationVmMapper;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('MEMBER')")
    public ResponseEntity<Map<String, String>> create(@RequestBody @Valid ParticipationCreateVM participationCreateVm) {
        Boolean registered = participationServiceImpl.create(participationCreateVm.getCompetition_code());
        HashMap<String, String> response = new HashMap<>();
        if (!registered){
            response.put("message", "Failed to register. Try again later.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Prepare success response
        response.put("message", "Registered successfully.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('JURY', 'ADMIN')")
    public ResponseEntity<Page<ParticipationVM>> findAll(Pageable pageable){
        Page<Participation> participations = participationServiceImpl.findAll(pageable);
        Page<ParticipationVM> participationVMS = participations.map(participationVmMapper::toParticipationVM);
        return new ResponseEntity<>(participationVMS , HttpStatus.OK);
    }
}
