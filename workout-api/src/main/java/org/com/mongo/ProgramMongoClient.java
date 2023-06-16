package org.com.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.com.dto.program_dto.AddProgramInMongoDto;
import org.com.dto.program_dto.UpdateProgramInMongoDto;
import org.com.dto.timestamp_dto.ProgramTimestampDto;
import org.com.model.Program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class ProgramMongoClient extends BaseMongoClient {
    public ProgramMongoClient() {
        super();
        this.entityCollection = this.mongoClient.getDatabase("workout").getCollection("program");
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

    public ProgramTimestampDto updateProgram(UpdateProgramInMongoDto program, List<ObjectId> seances, ObjectId _id) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        entityCollection.findOneAndReplace(
            eq("_id", _id),
            Document.parse(ow.writeValueAsString(program)).append("seances", seances)
        );
        return this.getProgramById(_id);
    }
}
