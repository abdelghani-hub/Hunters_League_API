package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.service.impl.ParticipationServiceImpl;
import com.youcode.hunters_league.web.vm.mapper.ParticipationCreateVmMapper;
import com.youcode.hunters_league.web.vm.participation.ParticipationCreateVM;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/participations")
public class ParticipationController {

    private ParticipationServiceImpl participationServiceImpl;

    public ParticipationController(ParticipationServiceImpl participationServiceImpl) {
        this.participationServiceImpl = participationServiceImpl;
    }

    @PostMapping("/create")
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
}
