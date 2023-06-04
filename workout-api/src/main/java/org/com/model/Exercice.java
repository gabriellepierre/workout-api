package org.com.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.com.serializer.ObjectIdDeserializer;

import java.util.List;

public class Exercice {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId _id;
    private String name;
    private List<String> musclesEngaged;
    private List<Set> sets;

    public Exercice(ObjectId _id, String name, List<String> musclesEngaged, List<Set> sets) {
        this._id = _id;
        this.name = name;
        this.musclesEngaged = musclesEngaged;
        this.sets = sets;
    }

    public Exercice(String name, List<String> musclesEngaged, List<Set> sets) {
        this._id = new ObjectId();
        this.name = name;
        this.musclesEngaged = musclesEngaged;
        this.sets = sets;
    }

    public Exercice() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMusclesEngaged() {
        return musclesEngaged;
    }

    public void setMusclesEngaged(List<String> musclesEngaged) {
        this.musclesEngaged = musclesEngaged;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
