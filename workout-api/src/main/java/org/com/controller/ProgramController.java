package org.com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.com.dto.program_dto.AddProgramInMongoDto;
import org.com.dto.program_dto.UpdateProgramInMongoDto;
import org.com.dto.program_dto.UpsertProgramDto;
import org.com.dto.timestamp_dto.ProgramTimestampDto;
import org.com.dto.user_dto.AddUserDto;
import org.com.dto.user_dto.UpdateUserBaseDto;
import org.com.dto.user_dto.UpdateUserDto;
import org.com.model.Program;
import org.com.model.User;
import org.com.mongo.ProgramMongoClient;
import org.com.mongo.UserMongoClient;

import java.io.IOException;
import java.util.List;

@Path("/programs")
public class ProgramController {
    @Inject
    private ProgramMongoClient programMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgramTimestampDto> getAllPrograms() throws IOException {
        return programMongoClient.getAllPrograms();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgramTimestampDto> researchPrograms(@QueryParam("field") String field, @QueryParam("value") String value) throws IOException {
        return programMongoClient.getProgramsBy(field, value);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ProgramTimestampDto getProgramById(@PathParam("id") String id) throws IOException {
        return programMongoClient.getProgramById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramTimestampDto addProgram(UpsertProgramDto addProgramDto) throws IOException {
        ObjectId newProgramId = programMongoClient.saveProgram(
            new AddProgramInMongoDto(
                addProgramDto.getName(),
                addProgramDto.getObjective(),
                addProgramDto.getLevel(),
                addProgramDto.getSeances().stream().map(ObjectId::new).toList()
            )
        ).getInsertedId().asObjectId().getValue();

        return programMongoClient.getProgramById(newProgramId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{_id}")
    public ProgramTimestampDto updateProgram(UpsertProgramDto program, @PathParam("_id") String _id) throws IOException {
        return programMongoClient.updateProgram(
            new UpdateProgramInMongoDto(
                program.getName(),
                program.getObjective(),
                program.getLevel()
            ),
            program.getSeances().stream().map(ObjectId::new).toList(),
            new ObjectId(_id));
    }
}
