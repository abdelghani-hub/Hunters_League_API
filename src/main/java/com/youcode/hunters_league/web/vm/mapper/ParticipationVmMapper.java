package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Participation;
import com.youcode.hunters_league.web.vm.participation.ParticipationCreateVM;
import com.youcode.hunters_league.web.vm.participation.ParticipationVM;
import org.mapstruct.Mapper;

public interface ParticipationVmMapper {
     Participation toParticipation(ParticipationCreateVM participationCreateVM);
     ParticipationCreateVM toParticipationCreateVM(Participation participation);

     ParticipationVM toParticipationVM(Participation participation);
}
