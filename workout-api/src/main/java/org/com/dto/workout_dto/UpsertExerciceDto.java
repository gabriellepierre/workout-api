package org.com.dto.workout_dto;

import org.com.model.Set;

import java.util.List;

public class UpsertExerciceDto {
    private String name;
    private List<String> musclesEngaged;
    private List<Set> sets;

    public UpsertExerciceDto(String name, List<String> musclesEngaged, List<Set> sets) {
        this.name = name;
        this.musclesEngaged = musclesEngaged;
        this.sets = sets;
    }

    public UpsertExerciceDto() {
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
