package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.AppUser;
import com.youcode.hunters_league.domain.Competition;
import com.youcode.hunters_league.domain.Participation;
import com.youcode.hunters_league.exception.NotValidConstraintException;
import com.youcode.hunters_league.repository.ParticipationRepository;
import com.youcode.hunters_league.service.ParticipationService;
import com.youcode.hunters_league.web.vm.mapper.CompetitionVmMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final CompetitionServiceImpl competitionServiceImpl;
    private final CompetitionVmMapper competitionVmMapper;
    private final UserServiceImpl userServiceImpl;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, CompetitionVmMapper competitionVmMapper, CompetitionServiceImpl competitionServiceImpl, UserServiceImpl userServiceImpl) {
        this.participationRepository = participationRepository;
        this.competitionServiceImpl = competitionServiceImpl;
        this.competitionVmMapper = competitionVmMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @Transactional
    public Boolean create(@Valid String competition_code) throws NotValidConstraintException {
        Competition competition = competitionServiceImpl.getCompetition(competition_code);
        // TODO auth : get the auth appUser
        AppUser appUser = userServiceImpl.findById(UUID.fromString("13e258c1-3b11-411a-92ef-f827cd88480f"));

        // Validation
        if(appUser.getLicenseExpirationDate().isBefore(competition.getDate()))
            throw new NotValidConstraintException("Your license is expired!");

        if (!competition.getOpenRegistration())
            throw new NotValidConstraintException("The competition is not open for registration!");

        if (competition.getDate().isBefore(LocalDateTime.now().plusHours(24)))
            throw new NotValidConstraintException("Registration is close!");

        if (competition.getParticipations().size() == competition.getMaxParticipants())
            throw new NotValidConstraintException("The competition is full!");

        if (!competition.getParticipations().isEmpty() && competition.getParticipations().stream().anyMatch(participation -> {
            if (participation.getAppUser() != null && participation.getAppUser().getId().equals(appUser.getId()))
                return true;
            return false;
        }))
            throw new NotValidConstraintException("You are already registered in this competition!");

        Participation participation = new Participation();
        participation.setCompetition(competition);
        participation.setAppUser(appUser);
        participationRepository.save(participation);
        return true;
    }
}