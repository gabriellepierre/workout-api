package org.com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.com.dto.timestamp_dto.ProgramTimestampDto;
import org.com.dto.timestamp_dto.WorkoutTimestampDto;
import org.com.dto.workout_dto.*;
import org.com.mongo.WorkoutMongoClient;

import java.io.IOException;
import java.util.List;

@Path("/workouts")
public class WorkoutController {
    @Inject
    private WorkoutMongoClient workoutMongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkoutTimestampDto> getAllWorkouts() throws IOException {
        return workoutMongoClient.getAllWorkouts();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkoutTimestampDto> researchWorkouts(@QueryParam("field") String field, @QueryParam("value") String value) throws IOException {
        return workoutMongoClient.getWorkoutsBy(field, value);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public WorkoutTimestampDto getWorkoutById(@PathParam("id") String id) throws IOException {
        return workoutMongoClient.getWorkoutById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WorkoutTimestampDto addWorkout(AddWorkoutDto addWorkoutDto) throws IOException {
        if (addWorkoutDto.getAuthor() == null) {
            ObjectId newWorkoutId = workoutMongoClient.saveWorkout(
                new UpsertWorkoutInMongoDto(
                    addWorkoutDto.getName(),
                    addWorkoutDto.getExercices().stream().map(exercice ->
                        new UpsertExerciceDto(
                            exercice.getName(),
                            exercice.getMusclesEngaged(),
                            exercice.getSets()
                        )
                    ).toList()
                ),
                null
            ).getInsertedId().asObjectId().getValue();

            return workoutMongoClient.getWorkoutById(newWorkoutId);
        } else {
            ObjectId newWorkoutId = workoutMongoClient.saveWorkout(
                new UpsertWorkoutInMongoDto(
                    addWorkoutDto.getName(),
                    addWorkoutDto.getExercices().stream().map(exercice ->
                        new UpsertExerciceDto(
                            exercice.getName(),
                            exercice.getMusclesEngaged(),
                            exercice.getSets()
                        )
                    ).toList()
                ),
                new ObjectId(addWorkoutDto.getAuthor())
            ).getInsertedId().asObjectId().getValue();

            return workoutMongoClient.getWorkoutById(newWorkoutId);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{_id}")
    public WorkoutTimestampDto updateWorkout(UpdateWorkoutDto updateWorkoutDto, @PathParam("_id") String _id) throws IOException {
        if (updateWorkoutDto.getAuthor() == null) {
            return workoutMongoClient.updateWorkout(
                new UpsertWorkoutInMongoDto(
                    updateWorkoutDto.getName(),
                    updateWorkoutDto.getExercices().stream().map(exercice ->
                        new UpsertExerciceDto(
                            exercice.getName(),
                            exercice.getMusclesEngaged(),
                            exercice.getSets()
                        )
                    ).toList()
                ),
                null,
                new ObjectId(_id)
            );
        } else {
            return workoutMongoClient.updateWorkout(
                new UpsertWorkoutInMongoDto(
                    updateWorkoutDto.getName(),
                    updateWorkoutDto.getExercices().stream().map(exercice ->
                        new UpsertExerciceDto(
                            exercice.getName(),
                            exercice.getMusclesEngaged(),
                            exercice.getSets()
                        )
                    ).toList()
                ),
                new ObjectId(updateWorkoutDto.getAuthor()),
                new ObjectId(_id)
            );
        }
    }
}
