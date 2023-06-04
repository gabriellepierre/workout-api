package org.com.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.com.serializer.ObjectIdDeserializer;

import java.util.List;

public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId _id;
    private String pseudo;
    private String password;
    private String email;
    private List<String> objectives;
    private List<String> programmes;

    public User(ObjectId _id, String pseudo, String password, String email, List<String> objectives, List<String> programmes) {
        this._id = _id;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.objectives = objectives;
        this.programmes = programmes;
    }

    public User() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
