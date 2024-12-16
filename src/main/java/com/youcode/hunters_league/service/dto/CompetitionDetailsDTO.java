package com.youcode.hunters_league.service.dto;
public class CompetitionDetailsDTO {
    private String code;
    private String location;
    private String date;
    private Integer participantsNumber;
    public CompetitionDetailsDTO() {
    }
    public CompetitionDetailsDTO(String code, String location, String date, Integer participantsNumber) {
        this.code = code;
        this.location = location;
        this.date = date;
        this.participantsNumber = participantsNumber;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Integer getParticipantsNumber() {
        return participantsNumber;
    }
    public void setParticipantsNumber(Integer participantsNumber) {
        this.participantsNumber = participantsNumber;
    }
}