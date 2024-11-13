package com.youcode.hunters_league.service.dto.mapper;
import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.service.dto.CompetitionDetailsDTO;
public interface CompetitionDetailsDtoMapper {
    CompetitionDetailsDTO toCompetitionDetailsDTO(Competition competition);
    Competition toCompetition(CompetitionDetailsDTO competitionDetailsDTO);
}