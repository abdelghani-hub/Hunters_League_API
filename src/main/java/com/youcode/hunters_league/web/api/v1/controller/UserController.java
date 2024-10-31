package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.exception.NullOrBlankArgException;
import com.youcode.hunters_league.service.impl.UserServiceImpl;
import com.youcode.hunters_league.web.vm.mapper.UserUpdateVmMapper;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import com.youcode.hunters_league.web.vm.user.UserUpdateVM;
import com.youcode.hunters_league.web.vm.user.UserVM;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("/update")
    public ResponseEntity<UserVM> update(@RequestBody @Valid UserUpdateVM userUpdateVM) {
        User user = userUpdateVmMapper.toUser(userUpdateVM);
        User updatedUser = userServiceImpl.update(user);
        UserVM userVM = userVmMapper.toUserVM(updatedUser);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(String id) throws NullOrBlankArgException {
        if (id == null || id.isEmpty())
            throw new NullOrBlankArgException("id");

        if (userServiceImpl.delete(UUID.fromString(id)))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
