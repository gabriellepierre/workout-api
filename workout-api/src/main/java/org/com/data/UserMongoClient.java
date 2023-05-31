package org.com.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.com.dto.AddUserInMongoDto;
import org.com.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class UserMongoClient {

    private MongoClient mongoClient;

    public UserMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost:27017/workout?authSource=admin");
        this.mongoClient = MongoClients.create(connectionString);
    }

    public InsertOneResult saveUser(AddUserInMongoDto u) {
        return this.mongoClient.getDatabase("workout").getCollection("user").insertOne(
            new Document()
                .append("pseudo", u.getPseudo())
                .append("password", u.getPassword())
                .append("email", u.getEmail())
                .append("objectives", u.getObjectives())
                .append("programmes", u.getProgrammes())
        );
    }

    public List<User> getAllUsers() throws JsonProcessingException {
        FindIterable<Document> documentsIterable = this.mongoClient.getDatabase("workout").getCollection("user").find();
        List<User> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            documents.add(new ObjectMapper().readValue(document.toJson(), User.class));
        }
        return documents;
    }

    public User getUserById(ObjectId _id) throws JsonProcessingException {
        MongoCollection<Document> collection = this.mongoClient.getDatabase("workout").getCollection("user");
        Document document = collection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No user with _id "+_id+" exists.");
        } else {
            return new ObjectMapper().readValue(document.toJson(), User.class);
        }
    }
}
