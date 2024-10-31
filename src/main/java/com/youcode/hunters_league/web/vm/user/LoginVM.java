package com.youcode.hunters_league.web.vm.user;

import jakarta.validation.constraints.NotBlank;

public class LoginVM {
    @NotBlank(message = "login field is required")
    private String login;

    @NotBlank(message = "Password is required")
    private String password;

    public LoginVM() {
    }

    public LoginVM(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}