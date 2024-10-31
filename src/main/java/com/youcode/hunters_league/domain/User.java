package com.youcode.hunters_league.domain;

import com.youcode.hunters_league.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

    @OneToMany(mappedBy = "user")
    private List<Participation> participations;

    public User() {
    }

    public User(UUID id, String username, String password, Role role, String firstName, String lastName, String cin, String email, String nationality, LocalDateTime joinDate, LocalDateTime licenseExpirationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.email = email;
        this.nationality = nationality;
        this.joinDate = joinDate;
        this.licenseExpirationDate = licenseExpirationDate;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public LocalDateTime getLicenseExpirationDate() {
        return licenseExpirationDate;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public void setLicenseExpirationDate(LocalDateTime licenseExpirationDate) {
        this.licenseExpirationDate = licenseExpirationDate;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
}

