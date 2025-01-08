package com.youcode.hunters_league.web.api.v1.controller;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.exception.EntityNotFoundException;
import com.youcode.hunters_league.exception.NullOrBlankArgException;
import com.youcode.hunters_league.service.impl.UserServiceImpl;
import com.youcode.hunters_league.web.vm.mapper.UserUpdateVmMapper;
import com.youcode.hunters_league.web.vm.mapper.UserVmMapper;
import com.youcode.hunters_league.web.vm.species.SpeciesVM;
import com.youcode.hunters_league.web.vm.user.UserUpdateVM;
import com.youcode.hunters_league.web.vm.user.UserVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
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
        AppUser appUser = userUpdateVmMapper.toUser(userUpdateVM);
        AppUser updatedAppUser = userServiceImpl.update(appUser);
        UserVM userVM = userVmMapper.toUserVM(updatedAppUser);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }


    // update by username ('users/update/username')
    @PutMapping("/update/{username}")
    public ResponseEntity<UserVM> updateByUsername(@PathVariable String username, @RequestBody @Valid UserUpdateVM userUpdateVM) {
        AppUser appUser = userUpdateVmMapper.toUser(userUpdateVM);
        AppUser updatedAppUser = userServiceImpl.updateByUsername(username, appUser);
        UserVM userVM = userVmMapper.toUserVM(updatedAppUser);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(String id) throws NullOrBlankArgException {
        if (id == null || id.isEmpty())
            throw new NullOrBlankArgException("id");

        Map<String, String> res = new HashMap<>();
        if (userServiceImpl.delete(UUID.fromString(id))){
            res.put("message", "AppUser deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        res.put("error", "AppUser not found");
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserVM>> search(String usernameORemail) throws NullOrBlankArgException {
        List<AppUser> appUsers = userServiceImpl.findByUsernameOrEmail(usernameORemail);
        List<UserVM> userVMs = appUsers.stream().map(userVmMapper::toUserVM).toList();
        return new ResponseEntity<>(userVMs, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserVM>> filterUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String cin
    ) {
        List<AppUser> filteredAppUsers = userServiceImpl.filter(firstName, lastName, cin);
        List<UserVM> userVMs = filteredAppUsers.stream().map(userVmMapper::toUserVM).toList();
        return new ResponseEntity<>(userVMs, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserVM>> findAll(Pageable pageable){
        Page<AppUser> users = userServiceImpl.findAll(pageable);
        Page<UserVM> farmResponseVM = users.map(userVmMapper::toUserVM);
        return new ResponseEntity<>(farmResponseVM , HttpStatus.OK);
    }

    // get by username
    @GetMapping("/{username}")
    public ResponseEntity<UserVM> findByUsername(@PathVariable String username) throws NullOrBlankArgException {
        if (username == null || username.isEmpty())
            throw new NullOrBlankArgException("(oo)username");

        AppUser appUser = userServiceImpl.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User")
        );
        UserVM userVM = userVmMapper.toUserVM(appUser);
        return new ResponseEntity<>(userVM, HttpStatus.OK);
    }
}
