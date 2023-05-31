package org.com.dto;

import org.bson.types.ObjectId;

import java.util.List;

public class AddUserInMongoDto {
    private String pseudo;
    private String password;
    private String email;
    private List<String> objectives;
    private List<String> programmes;

    public AddUserInMongoDto(String pseudo, String password, String email, List<String> objectives, List<String> programmes) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.objectives = objectives;
        this.programmes = programmes;
    }

    public AddUserInMongoDto() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives;
    }

    public List<String> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<String> programmes) {
        this.programmes = programmes;
    }
}
