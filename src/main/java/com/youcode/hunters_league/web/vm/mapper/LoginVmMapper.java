package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.web.vm.user.LoginVM;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LoginVmMapper {
     User toUser(LoginVM loginVM);
     LoginVM toLoginVM(User user);
}
