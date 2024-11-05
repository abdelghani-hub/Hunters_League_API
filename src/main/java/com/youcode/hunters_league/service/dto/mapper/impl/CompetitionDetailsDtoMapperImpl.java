package com.youcode.hunters_league.service.dto.mapper.impl;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.service.dto.CompetitionDetailsDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.youcode.hunters_league.service.dto.mapper.CompetitionDetailsDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDetailsDtoMapperImpl implements CompetitionDetailsDtoMapper {
    public CompetitionDetailsDtoMapperImpl() {
    }

    public CompetitionDetailsDTO toCompetitionDetailsDTO(Competition competition) {
        if (competition == null) {
            return null;
        } else {
            CompetitionDetailsDTO competitionDetailsDTO = new CompetitionDetailsDTO();
            competitionDetailsDTO.setCode(competition.getCode());
            competitionDetailsDTO.setLocation(competition.getLocation());
            // set participants number
            competitionDetailsDTO.setParticipantsNumber(competition.getParticipations().size());
            if (competition.getDate() != null) {
                competitionDetailsDTO.setDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(competition.getDate()));
            }

            return competitionDetailsDTO;
        }
    }

    public Competition toCompetition(CompetitionDetailsDTO competitionDetailsDTO) {
        if (competitionDetailsDTO == null) {
            return null;
        } else {
            Competition competition = new Competition();
            competition.setCode(competitionDetailsDTO.getCode());
            competition.setLocation(competitionDetailsDTO.getLocation());
            if (competitionDetailsDTO.getDate() != null) {
                competition.setDate(LocalDateTime.parse(competitionDetailsDTO.getDate()));
            }

            return competition;
        }
    }
}