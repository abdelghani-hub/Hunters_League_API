package com.youcode.hunters_league.web.vm.participation;

import com.youcode.hunters_league.web.vm.competition.CompetitionVM;
import com.youcode.hunters_league.web.vm.user.UserVM;

public class ParticipationVM {

    private CompetitionVM competitionVM;

    private UserVM userVM;

    private Double score;



    public ParticipationVM() {
    }

    public ParticipationVM(CompetitionVM competitionVM, UserVM userVM, Double score) {
        this.competitionVM = competitionVM;
        this.userVM = userVM;
        this.score = score;
    }

    public CompetitionVM getCompetitionVM() {
        return competitionVM;
    }

    public void setCompetitionVM(CompetitionVM competitionVM) {
        this.competitionVM = competitionVM;
    }

    public UserVM getUserVM() {
        return userVM;
    }

    public void setUserVM(UserVM userVM) {
        this.userVM = userVM;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
