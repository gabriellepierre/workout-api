package org.com.dto.program_dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.com.serializer.ObjectIdDeserializer;

import java.util.List;

public class AddProgramInMongoDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId _id;
    private String name;
    private String objective;
    private String level;
    private List<ObjectId> seances;

    public AddProgramInMongoDto(ObjectId _id, String name, String objective, String level, List<ObjectId> seances) {
        this._id = _id;
        this.name = name;
        this.objective = objective;
        this.level = level;
        this.seances = seances;
    }

    public AddProgramInMongoDto(String name, String objective, String level, List<ObjectId> seances) {
        this.name = name;
        this.objective = objective;
        this.level = level;
        this.seances = seances;
    }

    public AddProgramInMongoDto() {
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<ObjectId> getSeances() {
        return seances;
    }

    public void setSeances(List<ObjectId> seances) {
        this.seances = seances;
    }
}
