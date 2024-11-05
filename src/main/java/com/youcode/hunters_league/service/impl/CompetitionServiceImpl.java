package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.exception.InvalidCredentialsException;
import com.youcode.hunters_league.exception.NotValidConstraintException;
import com.youcode.hunters_league.repository.CompetitionRepository;
import com.youcode.hunters_league.service.CompetitionService;
import com.youcode.hunters_league.utils.LocalDateTimeUtil;
import com.youcode.hunters_league.web.vm.mapper.CompetitionVmMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionVmMapper competitionVmMapper;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionVmMapper competitionVmMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionVmMapper = competitionVmMapper;
    }

    @Override
    @Transactional
    public Competition save(@Valid Competition competition) {
        // Check max and min participants
        if (competition.getMinParticipants() > competition.getMaxParticipants()) {
            throw new InvalidCredentialsException("Minimum participants should be less than maximum participants");
        }

        // Check if the there is no competition with the same code
        if (competitionRepository.existsByCode(competition.getCode())) {
            throw new AlreadyExistException("code", competition.getCode());
        }

        // Check if there is no competition in the week
        if (!isTheWeekEmpty(competition.getDate(), null)) {
            throw new NotValidConstraintException("There is already a competition in the same week");
        }

        // Save & Map the user
        Competition savedCompetition = competitionRepository.save(competition);
        return savedCompetition;
    }

    @Override
    public Optional<Competition> findById(UUID id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Competition update(Competition competition) {
        // check if exists
        if (!competitionRepository.existsById(competition.getId()))
            return null;

        // Check if competition code already exists
        if (competitionRepository.existsByCodeAndIdNot(competition.getCode(), competition.getId())) {
            throw new AlreadyExistException("code", competition.getCode());
        }

        // Validate the competition week
        if (!isTheWeekEmpty(competition.getDate(), competition.getId())) {
            throw new NotValidConstraintException("There is already a competition in the same week");
        }
        return competitionRepository.save(competition);
    }

    @Override
    public boolean delete(UUID id) {
        if (competitionRepository.existsById(id)) {
            // delete
            competitionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean isTheWeekEmpty(LocalDateTime date, UUID competitionId) {
        LocalDateTime prevSunday = LocalDateTimeUtil.getStartOfWeek(date);
        LocalDateTime nextSaturday = LocalDateTimeUtil.getEndOfWeek(date);

        // If creating
        if (competitionId == null) {
            return !competitionRepository.existsByDateBetween(prevSunday, nextSaturday);
        }
        // If updating
        else {
            return !competitionRepository.existsByIdNotAndDateBetween(competitionId, prevSunday, nextSaturday);
        }
    }
}