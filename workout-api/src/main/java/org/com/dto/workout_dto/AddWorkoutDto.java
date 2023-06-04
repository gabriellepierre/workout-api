package org.com.dto.workout_dto;

import java.util.List;

public class AddWorkoutDto {
    private String author;
    private String name;
    private String type;
    private String dayOfWeek;
    private List<UpsertExerciceDto> exercices;

    public AddWorkoutDto(String author, String name, String type, String dayOfWeek, List<UpsertExerciceDto> exercices) {
        this.author = author;
        this.name = name;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<UpsertExerciceDto> getExercices() {
        return exercices;
    }

    public void setExercices(List<UpsertExerciceDto> exercices) {
        this.exercices = exercices;
    }
}
