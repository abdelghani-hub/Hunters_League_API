package com.youcode.hunters_league.dto.mapper;
import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.dto.CompetitionDetailsDTO;
public interface CompetitionDetailsDtoMapper {
    CompetitionDetailsDTO toCompetitionDetailsDTO(Competition competition);
    Competition toCompetition(CompetitionDetailsDTO competitionDetailsDTO);
}