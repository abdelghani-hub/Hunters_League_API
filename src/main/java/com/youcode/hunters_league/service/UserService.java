package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.domain.Species;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    AppUser save(@Valid AppUser appUser);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByCin(String cin);

    AppUser findById(UUID id);

    AppUser findByUsernameOrEmail(String username, String email);

    Page<AppUser> findAll(Pageable pageable);

    AppUser updateByUsername(String username, AppUser appUser);
}
