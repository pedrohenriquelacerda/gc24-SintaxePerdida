package com.caldeira.projetofinal.user.models;

public class UserRequestModel {
    private String firstName;
    private String lastName;

    public UserRequestModel() {}

    // getters e setters
    public String getFirstName() {
        return  firstName;
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
}
