package org.com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.com.data.UserMongoClient;
import org.com.dto.user_dto.AddUserDto;
import org.com.dto.user_dto.AddUserInMongoDto;
import org.com.dto.user_dto.UpdateUserDto;
import org.com.model.User;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserController {

    @Inject
    private UserMongoClient userMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws IOException {
        return userMongoClient.getAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public User getUserById(@PathParam("id") String id) throws IOException {
        System.out.println(id);
        return userMongoClient.getUserById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(AddUserDto addUserDto) throws IOException {
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{_id}")
    public User updateUser(UpdateUserDto user, @PathParam("_id") String _id) throws IOException, JSONException {
        return userMongoClient.updateUser(
            user,
            new ObjectId(_id)
        );
    }
}
