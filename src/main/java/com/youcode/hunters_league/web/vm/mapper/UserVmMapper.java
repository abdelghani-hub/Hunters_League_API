package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.web.vm.user.UserUpdateVM;
import com.youcode.hunters_league.web.vm.user.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserVmMapper {
    UserVM toUserVM(AppUser appUser);
    AppUser toUser(UserVM userVM);
}
