package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.exception.EntityNotFoundException;
import com.youcode.hunters_league.exception.InvalidCredentialsException;
import com.youcode.hunters_league.exception.NullOrBlankArgException;
import com.youcode.hunters_league.service.UserService;
import com.youcode.hunters_league.domain.enums.Role;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.repository.UserRepository;
import com.youcode.hunters_league.specification.UserSpecification;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserVmMapper userVmMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserVmMapper userVmMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userVmMapper = userVmMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public AppUser save(@Valid AppUser appUser) {
        // Check if the appUser already exists : username, email, cin
        if (this.findByUsername(appUser.getUsername()).isPresent()) {
            throw new AlreadyExistException("username", appUser.getUsername());
        }
        if (this.findByEmail(appUser.getEmail()).isPresent()) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }
        if (this.findByCin(appUser.getCin()).isPresent()) {
            throw new AlreadyExistException("cin", appUser.getCin());
        }

        // Check Encode password
        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        // Set the appUser role
        appUser.setRole(Role.MEMBER);

        // Set Join & License Expiration Dates
        appUser.setJoinDate(LocalDateTime.now());
        appUser.setLicenseExpirationDate(LocalDateTime.now().plusMonths(1)); // 1 month

        // Save & Map the appUser
        AppUser savedAppUser = userRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        if(username == null || username.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        if(email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByEmail(email);
    }

    public Optional<AppUser> login(String login, String password) throws InvalidCredentialsException {
        if (login == null || login.isEmpty()) {
            return Optional.empty();
        }
        Optional<AppUser> userOp = userRepository.findByUsernameOrEmail(login, login);
        if (userOp.isEmpty() || !passwordEncoder.matches(password, userOp.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return userOp;
    }

    @Override
    public Optional<AppUser> findByCin(String cin) {
        if(cin == null || cin.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByCin(cin);
    }

    @Override
    public AppUser findById(UUID id) {
        Optional<AppUser> userOP = userRepository.findById(id);
        if (!userOP.isPresent()) {
            throw new EntityNotFoundException("AppUser");
        }
        return userOP.get();
    }

    public AppUser update(AppUser appUser) {
        // Check if the appUser already exists id
        AppUser originalAppUser = this.findById(appUser.getId());

        // Check if the appUser already exists : username, email, cin
        if (userRepository.existsByUsernameAndIdNot(appUser.getUsername(), originalAppUser.getId())) {
            throw new AlreadyExistException("username", appUser.getUsername());
        }
        if (userRepository.existsByEmailAndIdNot(appUser.getEmail(), originalAppUser.getId())) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }
        if (userRepository.existsByCinAndIdNot(appUser.getCin(), originalAppUser.getId())) {
            throw new AlreadyExistException("cin", appUser.getCin());
        }

        // update
        appUser.setPassword(originalAppUser.getPassword());
        appUser.setRole(originalAppUser.getRole());
        appUser.setJoinDate(originalAppUser.getJoinDate());
        appUser.setLicenseExpirationDate(originalAppUser.getLicenseExpirationDate());
        return userRepository.save(appUser);
    }

    public boolean delete(UUID id) {
        if (userRepository.existsById(id)) {
            // delete
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AppUser> findByUsernameOrEmail(String usernameORemail) {
        if(usernameORemail == null || usernameORemail.isEmpty()) {
            throw new NullOrBlankArgException("username or email");
        }
        return userRepository.findByUsernameContainingOrEmailContaining(usernameORemail, usernameORemail);
    }

    public List<AppUser> filter(String firstName, String lastName, String cin) {
        final Specification<AppUser> specification = UserSpecification.filterUser(firstName, lastName, cin);
        final List<AppUser> appUsers = userRepository.findAll(specification);
        return appUsers;
    }
}