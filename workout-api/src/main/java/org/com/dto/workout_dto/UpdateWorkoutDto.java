package org.com.dto.workout_dto;

import org.bson.types.ObjectId;

import java.util.List;

public class UpdateWorkoutDto {
    private String author;
    private String name;
    private List<UpsertExerciceDto> exercices;

    public UpdateWorkoutDto(String author, String name, List<UpsertExerciceDto> exercices) {
        this.author = author;
        this.name = name;
        this.exercices = exercices;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
