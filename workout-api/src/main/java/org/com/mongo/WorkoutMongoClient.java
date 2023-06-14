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
import org.com.dto.timestamp_dto.WorkoutTimestampDto;
import org.com.dto.workout_dto.UpsertWorkoutInMongoDto;
import org.com.dto.workout_dto.UpdateWorkoutDto;
import org.com.model.Workout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class WorkoutMongoClient extends BaseMongoClient {
    public WorkoutMongoClient() {
        super();
        this.entityCollection = this.mongoClient.getDatabase("workout").getCollection("workout");
    }

    public InsertOneResult saveWorkout(UpsertWorkoutInMongoDto w, ObjectId authorId) {
        if (authorId == null ) {
            return entityCollection.insertOne(
                new Document()
                    .append("name", w.getName())
                    .append("exercices", w.getExercices())
            );
        } else {
            return entityCollection.insertOne(
                new Document()
                    .append("author", authorId)
                    .append("name", w.getName())
                    .append("exercices", w.getExercices())
            );
        }
    }

    public List<WorkoutTimestampDto> getAllWorkouts() throws IOException {
        FindIterable<Document> documentsIterable = entityCollection.find();
        List<WorkoutTimestampDto> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            Workout dbWorkout = new ObjectMapper().readValue(document.toJson(), Workout.class);
            documents.add(
                new WorkoutTimestampDto(
                    dbWorkout.get_id(),
                    dbWorkout.getAuthor(),
                    dbWorkout.getName(),
                    dbWorkout.getExercices()
                )
            );
        }
        return documents;
    }

    public WorkoutTimestampDto getWorkoutById(ObjectId _id) throws IOException {
        Document document = entityCollection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No workout with _id "+_id+" exists.");
        } else {
            Workout dbWorkout = new ObjectMapper().readValue(document.toJson(), Workout.class);
            return new WorkoutTimestampDto(
                dbWorkout.get_id(),
                dbWorkout.getAuthor(),
                dbWorkout.getName(),
                dbWorkout.getExercices()
            );
        }
    }

    public WorkoutTimestampDto updateWorkout(UpsertWorkoutInMongoDto workout, ObjectId authorId, ObjectId _id) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        if (authorId == null) {
            entityCollection.findOneAndReplace(
                eq("_id", _id),
                Document.parse(ow.writeValueAsString(workout))
            );
        } else {
            entityCollection.findOneAndReplace(
                eq("_id", _id),
                Document.parse(ow.writeValueAsString(workout)).append("author", authorId)
            );
        }
        return this.getWorkoutById(_id);
    }
}
