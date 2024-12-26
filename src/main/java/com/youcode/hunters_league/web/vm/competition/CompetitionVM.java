package com.youcode.hunters_league.web.vm.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youcode.hunters_league.annotation.CodeFormat;
import com.youcode.hunters_league.annotation.EnumValue;
import com.youcode.hunters_league.domain.enums.SpeciesType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompetitionVM {
    @NotBlank(message = "Code is required")
    @CodeFormat
    private String code;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must not be in the past!")
    private LocalDateTime date;

    @NotNull(message = "Species type is required")
    @EnumValue(enumClass = SpeciesType.class, message = "Species type must be one of 'SEA', 'BIG_GAME' or 'BIRD'")
    private String speciesType;

    @NotNull(message = "Minimum participants is required")
    @Min(value = 1, message = "Minimum participants should be at least 1")
    private Integer minParticipants;

    @NotNull(message = "Maximum participants is required")
    @Min(value = 1, message = "Maximum participants should be at least 1")
    private Integer maxParticipants;

    @NotNull(message = "Open registration status is required")
    private Boolean openRegistration;

    public CompetitionVM() {
    }

    public CompetitionVM(String code, String location, LocalDateTime date, String speciesType, Integer minParticipants, Integer maxParticipants, Boolean openRegistration) {
        this.code = code;
        this.location = location;
        this.date = date;
        this.speciesType = speciesType;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.openRegistration = openRegistration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(String speciesType) {
        this.speciesType = speciesType;
    }

    public Integer getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
        this.minParticipants = minParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Boolean getOpenRegistration() {
        return openRegistration;
    }

    public void setOpenRegistration(Boolean openRegistration) {
        this.openRegistration = openRegistration;
    }
}