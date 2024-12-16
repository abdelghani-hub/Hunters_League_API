package com.youcode.hunters_league.domain.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(
            Permission.CAN_MANAGE_COMPETITIONS,
            Permission.CAN_MANAGE_USERS,
            Permission.CAN_MANAGE_SPECIES
    )),

    MEMBER(Set.of(
            Permission.CAN_PARTICIPATE,
            Permission.CAN_VIEW_RANKING,
            Permission.CAN_VIEW_COMPETITIONS
    )),

    JURY(Set.of(
            Permission.CAN_SCORE,
            Permission.CAN_VIEW_RANKING,
            Permission.CAN_VIEW_COMPETITIONS
    ));

    public final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
