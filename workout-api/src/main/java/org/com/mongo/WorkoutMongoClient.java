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
import org.com.dto.workout_dto.UpsertWorkoutInMongoDto;
import org.com.dto.workout_dto.UpdateWorkoutDto;
import org.com.model.Workout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class WorkoutMongoClient {
    private MongoClient mongoClient;

    public WorkoutMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost:27017/workout?authSource=admin");
        this.mongoClient = MongoClients.create(connectionString);
    }

    public InsertOneResult saveWorkout(UpsertWorkoutInMongoDto w) {
        return this.mongoClient.getDatabase("workout").getCollection("workout").insertOne(
            new Document()
                .append("author", w.getAuthor())
                .append("name", w.getName())
                .append("type", w.getType())
                .append("dayOfWeek", w.getDayOfWeek())
                .append("exercices", w.getExercices())
        );
    }

    public List<Workout> getAllWorkouts() throws IOException {
        FindIterable<Document> documentsIterable = this.mongoClient.getDatabase("workout").getCollection("workout").find();
        List<Workout> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            documents.add(new ObjectMapper().readValue(document.toJson(), Workout.class));
        }
        return documents;
    }

    public Workout getWorkoutById(ObjectId _id) throws IOException {
        MongoCollection<Document> collection = this.mongoClient.getDatabase("workout").getCollection("workout");
        Document document = collection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No workout with _id "+_id+" exists.");
        } else {
            return new ObjectMapper().readValue(document.toJson(), Workout.class);
        }
    }

    public Workout updateWorkout(UpsertWorkoutInMongoDto workout, ObjectId _id) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.mongoClient.getDatabase("workout").getCollection("workout").findOneAndReplace(
            eq("_id", _id),
            Document.parse(ow.writeValueAsString(workout))
        );
        return this.getWorkoutById(_id);
    }
}
