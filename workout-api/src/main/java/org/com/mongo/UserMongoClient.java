package org.com.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.com.dto.timestamp_dto.UserTimestampDto;
import org.com.dto.user_dto.AddUserDto;
import org.com.dto.user_dto.UpdateUserBaseDto;
import org.com.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class UserMongoClient extends BaseMongoClient {

    public UserMongoClient() {
        super();
        this.entityCollection = this.mongoClient.getDatabase("workout").getCollection("user");
    }

    public InsertOneResult saveUser(AddUserDto u) {
        return entityCollection.insertOne(
            new Document()
                .append("pseudo", u.getPseudo())
                .append("password", u.getPassword())
                .append("email", u.getEmail())
        );
    }

    public List<UserTimestampDto> getAllUsers() throws IOException {
        FindIterable<Document> documentsIterable = entityCollection.find();
        List<UserTimestampDto> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            User dbUser = new ObjectMapper().readValue(document.toJson(), User.class);
            documents.add(
                new UserTimestampDto(
                    dbUser.get_id(),
                    dbUser.getPseudo(),
                    dbUser.getPassword(),
                    dbUser.getEmail(),
                    dbUser.getProgram()
                )
            );
        }
        return documents;
    }

    public UserTimestampDto getUserById(ObjectId _id) throws IOException {
        Document document = entityCollection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No user with _id "+_id+" exists.");
        } else {
            User dbUser = new ObjectMapper().readValue(document.toJson(), User.class);
            return new UserTimestampDto(
                dbUser.get_id(),
                dbUser.getPseudo(),
                dbUser.getPassword(),
                dbUser.getEmail(),
                dbUser.getProgram()
            );
        }
    }

    public UserTimestampDto updateUser(UpdateUserBaseDto user, ObjectId programId, ObjectId _id) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        if (programId == null) {
            entityCollection.findOneAndReplace(
                eq("_id", _id),
                Document.parse(ow.writeValueAsString(user))
            );
        } else {
            entityCollection.findOneAndReplace(
                eq("_id", _id),
                Document.parse(ow.writeValueAsString(user)).append("program", programId)
            );
        }
        return this.getUserById(_id);
    }
}
