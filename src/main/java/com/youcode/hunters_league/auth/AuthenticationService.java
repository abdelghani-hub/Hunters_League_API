package com.youcode.hunters_league.auth;

import com.youcode.hunters_league.config.JwtService;
import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.domain.enums.Role;
import com.youcode.hunters_league.dto.auth.LoginRequest;
import com.youcode.hunters_league.dto.auth.RegisterRequest;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.exception.InvalidCredentialsException;
import com.youcode.hunters_league.repository.UserRepository;
import com.youcode.hunters_league.dto.auth.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setEmail(request.getEmail());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setCin(request.getCin());
        appUser.setNationality(request.getNationality());
        appUser.setRole(Role.MEMBER);
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setJoinDate(LocalDateTime.now());
        appUser.setLicenseExpirationDate(LocalDateTime.now().plusMonths(1));

        // Check if the appUser already exists : username, email, cin
        if (userRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new AlreadyExistException("username", appUser.getUsername());
        }
        if (userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new AlreadyExistException("email", appUser.getEmail());
        }
        if (userRepository.findByCin(appUser.getCin()).isPresent()) {
            throw new AlreadyExistException("cin", appUser.getCin());
        }

        userRepository.save(appUser);
        String jwtToken = jwtService.generateToken(appUser);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        AppUser appUser = userRepository.findByUsernameOrEmail(request.getLogin(), request.getLogin())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        String jwtToken = jwtService.generateToken(appUser);
        return new AuthenticationResponse(jwtToken);
    }

}
