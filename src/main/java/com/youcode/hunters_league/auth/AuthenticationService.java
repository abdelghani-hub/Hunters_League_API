package com.youcode.hunters_league.auth;

import com.youcode.hunters_league.config.JwtService;
import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.dto.auth.LoginRequest;
import com.youcode.hunters_league.dto.auth.RegisterRequest;
import com.youcode.hunters_league.exception.EntityNotFoundException;
import com.youcode.hunters_league.service.UserService;
import com.youcode.hunters_league.dto.auth.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getConfirmPassword() == null || !request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(request.getPassword());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setCin(request.getCin());
        appUser.setNationality(request.getNationality());

        userService.save(appUser);
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
        AppUser appUser = userService.findByUsernameOrEmail(request.getLogin(), request.getLogin());

        String jwtToken = jwtService.generateToken(appUser);
        return new AuthenticationResponse(jwtToken);
    }

    public AppUser getAuthenticatedUser() {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        } else if (authentication != null) {
            username = authentication.getPrincipal().toString();
        }
        return userService
                        .findByUsername(username)
                        .orElseThrow(() -> new EntityNotFoundException("User"));
    }

}
