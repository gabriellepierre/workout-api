package org.com.model;

import org.bson.types.ObjectId;

import java.util.List;

public class User {
    private ObjectId id;
    private String pseudo;
    private String email;
    private String password;
    private List<String> objectives;
    private List<ObjectId> programmes;

    public User(ObjectId id, String pseudo, String email, String password, List<String> objectives, List<ObjectId> programmes) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.objectives = objectives;
        this.programmes = programmes;
    }

    public User() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public List<ObjectId> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<ObjectId> programmes) {
        this.programmes = programmes;
    }
}
