package org.com.dto.timestamp_dto;

import org.bson.types.ObjectId;
import org.com.model.Program;

import java.util.List;

public class ProgramTimestampDto extends Program {
    private String createdAt;

    public ProgramTimestampDto(ObjectId _id, String name, String objective, String level, List<ObjectId> seances) {
        super(_id, name, objective, level, seances);
        this.createdAt = _id.getDate().toInstant().toString();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
