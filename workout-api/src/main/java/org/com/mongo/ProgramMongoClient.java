package org.com.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.com.dto.program_dto.AddProgramInMongoDto;
import org.com.dto.program_dto.UpdateProgramInMongoDto;
import org.com.dto.timestamp_dto.ProgramTimestampDto;
import org.com.model.Program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Accumulators.addToSet;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

@ApplicationScoped
public class ProgramMongoClient extends BaseMongoClient {
    public ProgramMongoClient() {
        super();
        this.entityCollection = this.mongoClient.getDatabase("workout").getCollection("program");
        // Single-field index sur objective
        entityCollection.createIndex(Indexes.ascending("objective"));
        // Single-field index sur level
        entityCollection.createIndex(Indexes.ascending("level"));
        // Compound index sur objective ET level
        entityCollection.createIndex(Indexes.ascending("objective", "level"));
    }

    public InsertOneResult saveProgram(AddProgramInMongoDto p) {
        return entityCollection.insertOne(
            new Document()
                .append("name", p.getName())
                .append("objective", p.getObjective())
                .append("level", p.getLevel())
                .append("seances", p.getSeances())
        );
    }

    public List<ProgramTimestampDto> getAllPrograms() throws IOException {
        FindIterable<Document> documentsIterable = entityCollection.find();
        List<ProgramTimestampDto> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            Program dbProgram = new ObjectMapper().readValue(document.toJson(), Program.class);
            documents.add(
                new ProgramTimestampDto(
                    dbProgram.get_id(),
                    dbProgram.getName(),
                    dbProgram.getObjective(),
                    dbProgram.getLevel(),
                    dbProgram.getSeances()
                )
            );
        }
        return documents;
    }

    public ProgramTimestampDto getProgramById(ObjectId _id) throws IOException {
        Document document = entityCollection.find(eq("_id", _id)).first();
        if (document == null) {
            throw new IllegalArgumentException("No program with _id "+_id+" exists.");
        } else {
            Program dbProgram = new ObjectMapper().readValue(document.toJson(), Program.class);
            return new ProgramTimestampDto(
                dbProgram.get_id(),
                dbProgram.getName(),
                dbProgram.getObjective(),
                dbProgram.getLevel(),
                dbProgram.getSeances()
            );
        }
    }

    public List<ProgramTimestampDto> getProgramsBy(String field, String value) throws IOException {
        FindIterable<Document> documentsIterable = entityCollection.find(eq(field, value));
        List<ProgramTimestampDto> documents = new ArrayList<>();
        for (Document document : documentsIterable) {
            Program dbProgram = new ObjectMapper().readValue(document.toJson(), Program.class);
            documents.add(
                new ProgramTimestampDto(
                    dbProgram.get_id(),
                    dbProgram.getName(),
                    dbProgram.getObjective(),
                    dbProgram.getLevel(),
                    dbProgram.getSeances()
                )
            );
        }
        return documents;
    }

    public List<ProgramTimestampDto> getProgramsUsingAggregate(String field, String value) throws IOException {
        List<BsonDocument> pipeline = Stream.of(
            group("$"+field, sum(field, "$champ_a_agreger")),
            project(fields(
                excludeId(),
                include(field)
            ))
        ).map(Bson::toBsonDocument).toList();

        List<ProgramTimestampDto> documents = new ArrayList<>();
        for (BsonDocument document : pipeline) {
            Program dbProgram = new ObjectMapper().readValue(document.toJson(), Program.class);
            documents.add(
                new ProgramTimestampDto(
                    dbProgram.get_id(),
                    dbProgram.getName(),
                    dbProgram.getObjective(),
                    dbProgram.getLevel(),
                    dbProgram.getSeances()
                )
            );
        }
        return documents;
    }

    public ProgramTimestampDto updateProgram(UpdateProgramInMongoDto program, List<ObjectId> seances, ObjectId _id) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        entityCollection.findOneAndReplace(
            eq("_id", _id),
            Document.parse(ow.writeValueAsString(program)).append("seances", seances)
        );
        return this.getProgramById(_id);
    }

    public DeleteResult deleteProgram(ObjectId id) {
        return entityCollection.deleteOne(new Document("_id", id));
    }
}
