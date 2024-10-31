package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.User;
import com.youcode.hunters_league.web.vm.user.RegisterVM;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterVmMapper {
     User toUser(RegisterVM registerVM);
     RegisterVM toRegisterVM(User user);
}
