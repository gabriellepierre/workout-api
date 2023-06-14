package org.com.dto.program_dto;

import org.bson.types.ObjectId;

import java.util.List;

public class UpsertProgramDto {
    private String name;
    private String objective;
    private String level;
    private List<String> seances;

    public UpsertProgramDto(String name, String objective, String level, List<String> seances) {
        this.name = name;
        this.objective = objective;
        this.level = level;
        this.seances = seances;
    }

    public UpsertProgramDto() {
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

    public List<String> getSeances() {
        return seances;
    }

    public void setSeances(List<String> seances) {
        this.seances = seances;
    }
}
