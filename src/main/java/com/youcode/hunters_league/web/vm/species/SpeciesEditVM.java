package com.youcode.hunters_league.web.vm.species;

import com.youcode.hunters_league.annotation.EnumValue;
import com.youcode.hunters_league.domain.enums.Difficulty;
import com.youcode.hunters_league.domain.enums.SpeciesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class SpeciesEditVM {

    private UUID id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Category is required")
    @EnumValue(enumClass = SpeciesType.class, message = "Category must be one of 'SEA', 'BIG_GAME' or 'BIRD'")
    private String category;

    @NotNull(message = "Minimum weight is required")
    @Positive(message = "Minimum weight must be positive")
    private Double minimumWeight;

    @NotNull(message = "Difficulty is required")
    @EnumValue(enumClass = Difficulty.class, message = "Difficulty must be one of 'COMMON' ,'RARE', 'EPIC' or 'LEGENDARY'")
    private String difficulty;

    @NotNull(message = "Points is required")
    @Positive(message = "Points must be positive")
    private Integer points;

    public SpeciesEditVM() {
    }

    public SpeciesEditVM(String name, String category, Double minimumWeight, String difficulty, Integer points) {
        this.name = name;
        this.category = category;
        this.minimumWeight = minimumWeight;
        this.difficulty = difficulty;
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(Double minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}