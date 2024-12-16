package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.web.vm.user.UserUpdateVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUpdateVmMapper {
    UserUpdateVM toUserUpdateVM(AppUser appUser);
    AppUser toUser(UserUpdateVM userVM);
}
