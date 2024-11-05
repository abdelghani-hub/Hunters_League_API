package com.youcode.hunters_league.validator;

import com.youcode.hunters_league.annotation.CodeFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CodeFormatValidator implements ConstraintValidator<CodeFormat, String> {
    private static final Pattern CODE_PATTERN = Pattern.compile("^(\\w+)-(\\d{4}-\\d{2}-\\d{2})$");

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        return code != null && CODE_PATTERN.matcher(code).matches();
    }
}
