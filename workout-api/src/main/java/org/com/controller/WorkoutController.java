package org.com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.com.dto.workout_dto.*;
import org.com.model.Workout;
import org.com.mongo.WorkoutMongoClient;

import java.io.IOException;
import java.util.List;

@Path("/workouts")
public class WorkoutController {
    @Inject
    private WorkoutMongoClient workoutMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Workout> getAllWorkouts() throws IOException {
        return workoutMongoClient.getAllWorkouts();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Workout getWorkoutById(@PathParam("id") String id) throws IOException {
        return workoutMongoClient.getWorkoutById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Workout addWorkout(AddWorkoutDto addWorkoutDto) throws IOException {
        ObjectId newWorkoutId = workoutMongoClient.saveWorkout(
            new UpsertWorkoutInMongoDto(
                new ObjectId(addWorkoutDto.getAuthor()),
                addWorkoutDto.getName(),
                addWorkoutDto.getType(),
                addWorkoutDto.getDayOfWeek(),
                addWorkoutDto.getExercices().stream().map( exercice ->
                    new UpsertExerciceInMongoDto(
                        new ObjectId(),
                        exercice.getName(),
                        exercice.getMusclesEngaged(),
                        exercice.getSets()
                    )
                ).toList()
            )
        ).getInsertedId().asObjectId().getValue();

        return workoutMongoClient.getWorkoutById(newWorkoutId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{_id}")
    public Workout updateWorkout(UpdateWorkoutDto updateWorkoutDto, @PathParam("_id") String _id) throws IOException {
        return workoutMongoClient.updateWorkout(
            new UpsertWorkoutInMongoDto(
                new ObjectId(updateWorkoutDto.getAuthor()),
                updateWorkoutDto.getName(),
                updateWorkoutDto.getType(),
                updateWorkoutDto.getDayOfWeek(),
                updateWorkoutDto.getExercices().stream().map( exercice ->
                    new UpsertExerciceInMongoDto(
                        new ObjectId(),
                        exercice.getName(),
                        exercice.getMusclesEngaged(),
                        exercice.getSets()
                    )
                ).toList()
            ),
            new ObjectId(_id)
        );
    }
}
