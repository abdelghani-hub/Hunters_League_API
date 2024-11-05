package com.youcode.hunters_league.annotation;

import com.youcode.hunters_league.validator.UniqueSpeciesNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueSpeciesNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSpeciesName {
    String message() default "Species already exists";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}