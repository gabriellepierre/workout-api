package org.com.dto.workout_dto;

import java.util.List;

public class UpsertWorkoutInMongoDto {
    private String name;
    private List<UpsertExerciceInMongoDto> exercices;

    public UpsertWorkoutInMongoDto( String name, List<UpsertExerciceInMongoDto> exercices) {
        this.name = name;
        this.exercices = exercices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UpsertExerciceInMongoDto> getExercices() {
        return exercices;
    }

    public void setExercices(List<UpsertExerciceInMongoDto> exercices) {
        this.exercices = exercices;
    }
}
