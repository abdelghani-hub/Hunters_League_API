package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.exception.InvalidCredentialsException;
import com.youcode.hunters_league.exception.MismatchException;
import com.youcode.hunters_league.web.vm.mapper.LoginVmMapper;
import com.youcode.hunters_league.web.vm.user.LoginVM;
import com.youcode.hunters_league.web.vm.user.UserVM;
import com.youcode.hunters_league.service.impl.UserServiceImpl;
import com.youcode.hunters_league.web.vm.user.RegisterVM;
import com.youcode.hunters_league.web.vm.mapper.RegisterVmMapper;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserServiceImpl userServiceImpl;
    private RegisterVmMapper registerVmMapper;
    private UserVmMapper userVmMapper;
    private LoginVmMapper loginVmMapper;

    public AuthController(UserServiceImpl userServiceImpl, RegisterVmMapper registerVmMapper, UserVmMapper userVmMapper, LoginVmMapper loginVmMapper) {
        this.userServiceImpl = userServiceImpl;
        this.registerVmMapper = registerVmMapper;
        this.userVmMapper = userVmMapper;
        this.loginVmMapper = loginVmMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVM> register(@RequestBody @Valid RegisterVM registerVM) {
        // Check password and confirm password
        if (!registerVM.getPassword().equals(registerVM.getConfirmPassword())) {
            throw new MismatchException("confirmPassword", "Password and Confirm Password do not match");
        }
        User user = registerVmMapper.toUser(registerVM);
        User savedUser = userServiceImpl.save(user);
        UserVM userVM = userVmMapper.toUserVM(savedUser);
        return new ResponseEntity<>(userVM, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserVM> login(@RequestBody @Valid LoginVM loginVM) {
        Optional<User> userOptional = userServiceImpl.login(loginVM.getLogin(), loginVM.getPassword());
        User user = userOptional.orElseThrow(() -> new InvalidCredentialsException("Invalid Credentials"));
        UserVM userVM = userVmMapper.toUserVM(user);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }
}
