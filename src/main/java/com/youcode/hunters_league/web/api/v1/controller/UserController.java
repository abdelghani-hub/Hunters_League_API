package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.service.impl.UserServiceImpl;
import com.youcode.hunters_league.web.vm.mapper.UserUpdateVmMapper;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import com.youcode.hunters_league.web.vm.user.UserUpdateVM;
import com.youcode.hunters_league.web.vm.user.UserVM;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserServiceImpl userServiceImpl;
    private UserVmMapper userVmMapper;
    private UserUpdateVmMapper userUpdateVmMapper;

    public UserController(UserServiceImpl userServiceImpl, UserVmMapper userVmMapper, UserUpdateVmMapper userUpdateVmMapper) {
        this.userServiceImpl = userServiceImpl;
        this.userVmMapper = userVmMapper;
        this.userUpdateVmMapper = userUpdateVmMapper;
    }

    @PostMapping("/update")
    public ResponseEntity<UserVM> update(@RequestBody @Valid UserUpdateVM userUpdateVM) {
        User user = userUpdateVmMapper.toUser(userUpdateVM);
        User updatedUser = userServiceImpl.update(user);
        UserVM userVM = userVmMapper.toUserVM(updatedUser);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }
}
