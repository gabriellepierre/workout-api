package org.com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.com.data.UserMongoClient;
import org.com.dto.AddUserDto;
import org.com.dto.AddUserInMongoDto;
import org.com.model.User;

import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserController {

    @Inject
    private UserMongoClient userMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws JsonProcessingException {
        return userMongoClient.getAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public User getUserById(@PathParam("id") String id) throws JsonProcessingException {
        System.out.println(id);
        return userMongoClient.getUserById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(AddUserDto addUserDto) throws JsonProcessingException {
        ObjectId newUserId = userMongoClient.saveUser(
            new AddUserInMongoDto(
                addUserDto.getPseudo(),
                addUserDto.getPassword(),
                addUserDto.getEmail(),
                new ArrayList<>(),
                new ArrayList<>()
            )
        ).getInsertedId().asObjectId().getValue();

        return userMongoClient.getUserById(newUserId);
    }
}
