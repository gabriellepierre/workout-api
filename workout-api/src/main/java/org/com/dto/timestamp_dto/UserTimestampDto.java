package org.com.dto.timestamp_dto;

import org.bson.types.ObjectId;
import org.com.model.User;

public class UserTimestampDto extends User {
    private String createdAt;

    public UserTimestampDto(ObjectId _id, String pseudo, String password, String email, ObjectId program) {
        super(_id, pseudo, password, email, program);
        this.createdAt = _id.getDate().toInstant().toString();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
