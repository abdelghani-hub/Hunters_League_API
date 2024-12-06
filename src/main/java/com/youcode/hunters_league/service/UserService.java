package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.AppUser;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    AppUser save(@Valid AppUser appUser);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByCin(String cin);

    AppUser findById(UUID id);

    AppUser findByUsernameOrEmail(String username, String email);
}
