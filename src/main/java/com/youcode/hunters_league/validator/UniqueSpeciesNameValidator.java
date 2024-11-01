package com.youcode.hunters_league.validator;

import com.youcode.hunters_league.annotation.UniqueSpeciesName;
import com.youcode.hunters_league.repository.SpeciesRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueSpeciesNameValidator implements ConstraintValidator<UniqueSpeciesName, String> {
    @Autowired
    SpeciesRepository speciesRepository;

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return speciesRepository.findByName(value).isEmpty();
    }
}