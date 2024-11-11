package com.youcode.hunters_league.web.vm.participation;

import jakarta.validation.constraints.NotBlank;

public class ParticipationCreateVM {

    @NotBlank(message = "participant_code is required")
    private String competition_code;

    public ParticipationCreateVM() {
    }

    public ParticipationCreateVM(String competition_code) {
        this.competition_code = competition_code;
    }


    public String getCompetition_code() {
        return competition_code;
    }

    public void setCompetition_code(String competition_code) {
        this.competition_code = competition_code;
    }


}
