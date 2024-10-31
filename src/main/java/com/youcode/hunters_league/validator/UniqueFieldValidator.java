package com.youcode.hunters_league.validator;

import com.youcode.hunters_league.annotation.UniqueField;
import com.youcode.hunters_league.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {
    @Autowired
    UserRepository userRepository;

    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (fieldName.equals("username")) {
            return userRepository.findByUsername(value).isEmpty();
        } else if (fieldName.equals("email")) {
            return userRepository.findByEmail(value).isEmpty();
        } else if (fieldName.equals("cin")) {
            return userRepository.findByCin(value).isEmpty();
        }
        return true;
    }
}