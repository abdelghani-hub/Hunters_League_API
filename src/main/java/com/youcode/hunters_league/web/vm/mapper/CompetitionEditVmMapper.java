package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.web.vm.competition.CompetitionEditVM;
import com.youcode.hunters_league.web.vm.competition.CompetitionVM;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CompetitionEditVmMapper {
     Competition toCompetition(CompetitionEditVM competitionEditVM);
     CompetitionEditVM toCompetitionEditVM(Competition competition);
}
