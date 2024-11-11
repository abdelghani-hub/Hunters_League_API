package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Participation;
import com.youcode.hunters_league.web.vm.participation.ParticipationCreateVM;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ParticipationCreateVmMapper {
     Participation toParticipation(ParticipationCreateVM participationCreateVM);
     ParticipationCreateVM toParticipationCreateVM(Participation participation);
}
