package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.web.vm.user.UserVM;
import com.youcode.hunters_league.service.impl.UserServiceImpl;
import com.youcode.hunters_league.web.vm.user.RegisterVM;
import com.youcode.hunters_league.web.vm.mapper.RegisterVmMapper;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserServiceImpl userServiceImpl;
    private RegisterVmMapper registerVmMapper;
    private UserVmMapper userVmMapper;

    public AuthController(UserServiceImpl userServiceImpl, RegisterVmMapper registerVmMapper, UserVmMapper userVmMapper) {
        this.userServiceImpl = userServiceImpl;
        this.registerVmMapper = registerVmMapper;
        this.userVmMapper = userVmMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVM> register(@Valid @RequestBody RegisterVM registerVM) {
        User user = registerVmMapper.toUser(registerVM);
        User savedUser = userServiceImpl.save(user);
        UserVM userVM = userVmMapper.toUserVM(savedUser);
        return new ResponseEntity<>(userVM, HttpStatus.CREATED);
    }
}
