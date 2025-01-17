package com.youcode.hunters_league.web.vm.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class UserUpdateVM {

    private UUID id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "CIN is required")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "CIN is not valid")
    private String cin;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    public UserUpdateVM() {
    }

    public UserUpdateVM(String firstName, String lastName, String username, String email, String cin, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.cin = cin;
        this.nationality = nationality;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}