package com.youcode.hunters_league.web.vm.user;

import com.youcode.hunters_league.annotation.UniqueField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class RegisterVM {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @UniqueField(fieldName = "username", message = "Username already exists")
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @UniqueField(fieldName = "email", message = "Email already exists")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain an uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain a lowercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain a number")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "CIN is required")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "CIN is not valid")
    @UniqueField(fieldName = "cin", message = "CIN already exists")
    private String cin;

    @NotBlank(message = "Nationality is required")
    private String nationality;
}