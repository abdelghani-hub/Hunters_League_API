package com.youcode.hunters_league.web.vm.user;

import jakarta.validation.constraints.NotBlank;

public class SearchUserVM {
    @NotBlank(message = "username or email is required")
    private String usernameORemail;
}