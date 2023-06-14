package org.com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.com.dto.timestamp_dto.UserTimestampDto;
import org.com.dto.user_dto.*;
import org.com.mongo.UserMongoClient;
import org.com.model.User;

import java.io.IOException;
import java.util.List;

@Path("/users")
public class UserController {

    @Inject
    private UserMongoClient userMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserTimestampDto> getAllUsers() throws IOException {
        return userMongoClient.getAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public UserTimestampDto getUserById(@PathParam("id") String id) throws IOException {
        return userMongoClient.getUserById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserTimestampDto addUser(AddUserDto addUserDto) throws IOException {
        ObjectId newUserId = userMongoClient.saveUser(
            new AddUserDto(
                addUserDto.getPseudo(),
                addUserDto.getPassword(),
                addUserDto.getEmail()
            )
        ).getInsertedId().asObjectId().getValue();

        return userMongoClient.getUserById(newUserId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{_id}")
    public UserTimestampDto updateUser(UpdateUserDto user, @PathParam("_id") String _id) throws IOException {
        if(user.getProgram() == null) {
            return userMongoClient.updateUser(
                new UpdateUserBaseDto(
                    user.getPseudo(),
                    user.getPassword(),
                    user.getEmail()
                ),
                null,
                new ObjectId(_id)
            );
        } else {
            return userMongoClient.updateUser(
                new UpdateUserBaseDto(
                    user.getPseudo(),
                    user.getPassword(),
                    user.getEmail()
                ),
                new ObjectId(user.getProgram()),
                new ObjectId(_id)
            );
        }
    }
}
