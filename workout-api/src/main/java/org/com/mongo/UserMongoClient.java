package org.com.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;
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

    public List<User> getAllUsers() throws IOException {
        FindIterable<Document> documentsIterable = entityCollection.find();
        List<User> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            documents.add(new ObjectMapper().readValue(document.toJson(), User.class));
        }
        return documents;
    }

    public User getUserById(ObjectId _id) throws IOException {
        Document document = entityCollection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No user with _id "+_id+" exists.");
        } else {
            return new ObjectMapper().readValue(document.toJson(), User.class);
        }
    }

    public User updateUser(UpdateUserBaseDto user, ObjectId programId, ObjectId _id) throws IOException {
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
