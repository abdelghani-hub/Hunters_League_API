package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.web.vm.user.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserVmMapper {
    UserVM toUserVM(User user);
    User toUser(UserVM userVM);
}
