package com.youcode.hunters_league.web.vm.user;


import com.youcode.hunters_league.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVM {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String cin;
    private Role role;
}
