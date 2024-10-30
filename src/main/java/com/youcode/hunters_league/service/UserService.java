package com.youcode.hunters_league.service;

import com.youcode.hunters_league.domain.User;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User save(@Valid User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByCin(String cin);

    boolean isExistsByField(String fieldName, String value);

    Optional<User> findById(UUID id);
}
