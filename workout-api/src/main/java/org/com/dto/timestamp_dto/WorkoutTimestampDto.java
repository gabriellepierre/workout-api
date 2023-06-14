package org.com.dto.timestamp_dto;

import org.bson.types.ObjectId;
import org.com.model.Exercice;
import org.com.model.Workout;

import java.util.List;

public class WorkoutTimestampDto extends Workout {
    private String createdAt;

    public WorkoutTimestampDto(ObjectId _id, ObjectId author, String name, List<Exercice> exercices) {
        super(_id, author, name, exercices);
        this.createdAt = _id.getDate().toInstant().toString();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
