package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Participation;
import com.youcode.hunters_league.web.vm.participation.ParticipationCreateVM;
import com.youcode.hunters_league.web.vm.participation.ParticipationVM;
import org.springframework.stereotype.Component;

@Component
public class ParticipationVmMapperImpl implements ParticipationVmMapper {

    private final CompetitionVmMapper competitionMapper;
    private final UserVmMapper userVmMapper;

    public ParticipationVmMapperImpl(CompetitionVmMapper competitionMapper, UserVmMapper userVmMapper) {
        this.competitionMapper = competitionMapper;
        this.userVmMapper = userVmMapper;
    }

    @Override
    public Participation toParticipation(ParticipationCreateVM participationCreateVM) {
        if ( participationCreateVM == null ) {
            return null;
        }

        Participation participation = new Participation();

        return participation;
    }

    @Override
    public ParticipationCreateVM toParticipationCreateVM(Participation participation) {
        if ( participation == null ) {
            return null;
        }

        ParticipationCreateVM participationCreateVM = new ParticipationCreateVM();

        return participationCreateVM;
    }

    @Override
    public ParticipationVM toParticipationVM(Participation participation) {
        if ( participation == null ) {
            return null;
        }

        ParticipationVM participationVM = new ParticipationVM();

        participationVM.setCompetitionVM( competitionMapper.toCompetitionVM( participation.getCompetition() ) );
        participationVM.setUserVM( userVmMapper.toUserVM( participation.getAppUser() ) );
        participationVM.setScore( participation.getScore() );

        return participationVM;
    }
}
