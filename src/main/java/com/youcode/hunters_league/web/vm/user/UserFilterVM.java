package com.youcode.hunters_league.web.vm.user;

public class UserFilterVM {
    private String firstName;
    private String lastName;
    private String cin;

    public UserFilterVM() {
    }

    public UserFilterVM(String firstName, String lastName, String cin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
