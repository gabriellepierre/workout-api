package org.com.dto.workout_dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.com.serializer.ObjectIdDeserializer;

import java.util.List;

public class UpsertWorkoutInMongoDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    private ObjectId author;
    private String name;
    private String type;
    private String dayOfWeek;
    private List<UpsertExerciceInMongoDto> exercices;

    public UpsertWorkoutInMongoDto(ObjectId author, String name, String type, String dayOfWeek, List<UpsertExerciceInMongoDto> exercices) {
        this.author = author;
        this.name = name;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.exercices = exercices;
    }

    public ObjectId getAuthor() {
        return author;
    }

    public void setAuthor(ObjectId author) {
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

    public List<UpsertExerciceInMongoDto> getExercices() {
        return exercices;
    }

    public void setExercices(List<UpsertExerciceInMongoDto> exercices) {
        this.exercices = exercices;
    }
}
