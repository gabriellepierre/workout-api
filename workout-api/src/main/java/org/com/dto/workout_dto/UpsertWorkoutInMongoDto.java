package org.com.dto.workout_dto;

import java.util.List;

public class UpsertWorkoutInMongoDto {
    private String name;
    private List<UpsertExerciceDto> exercices;

    public UpsertWorkoutInMongoDto( String name, List<UpsertExerciceDto> exercices) {
        this.name = name;
        this.exercices = exercices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UpsertExerciceDto> getExercices() {
        return exercices;
    }

    public void setExercices(List<UpsertExerciceDto> exercices) {
        this.exercices = exercices;
    }
}
