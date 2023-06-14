package org.com.dto.program_dto;

import org.bson.types.ObjectId;

import java.util.List;

public class UpdateProgramInMongoDto {
    private String name;
    private String objective;
    private String level;

    public UpdateProgramInMongoDto(String name, String objective, String level) {
        this.name = name;
        this.objective = objective;
        this.level = level;
    }

    public UpdateProgramInMongoDto() {
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
}
