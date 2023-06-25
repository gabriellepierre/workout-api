package org.com;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ResponseBody;
import org.com.dto.timestamp_dto.UserTimestampDto;
import org.com.dto.user_dto.AddUserDto;
import org.com.dto.user_dto.UpdateUserDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserControllerTest {

    @Test
    public void testAddUserEndpoint() {
        given()
            .contentType("application/json")
            .body(new AddUserDto("pseudoCool", "mdpEncorePlusCool", "email@gmail.com"))
            .when().post("/users")
            .then()
            .statusCode(200)
            .body("pseudo", is("pseudoCool"))
            .body("password", is("mdpEncorePlusCool"))
            .body("email", is("email@gmail.com"));
    }

    @Test
    public void testGetUserByIdEndpoint() throws JSONException {
        String responseString = given()
            .contentType("application/json")
            .body(new AddUserDto("pseudo2", "mdp2", "email2@gmail.com"))
            .when().post("/users")
            .body()
            .print();

        JSONObject jsonObject = new JSONObject(responseString);
        String _id = jsonObject.getString("_id");

        given()
            .when().get("/users/"+_id)
            .then()
            .statusCode(200)
            .body("pseudo", is("pseudo2"))
            .body("password", is("mdp2"))
            .body("email", is("email2@gmail.com"));
    }

    @Test
    public void testEditUserByIdEndpoint() throws JSONException {
        String responseString = given()
            .contentType("application/json")
            .body(new AddUserDto("pseudo3", "mdp3", "email3@gmail.com"))
            .when().post("/users")
            .body()
            .print();

        JSONObject jsonObject = new JSONObject(responseString);
        String _id = jsonObject.getString("_id");

        given()
            .contentType("application/json")
            .body(new UpdateUserDto("pseudo4", "mdp4", "email4@gmail.com", "64779939a753eb2cbf0abb4f"))
            .when().put("/users/"+_id)
            .then()
            .statusCode(200)
            .body("pseudo", is("pseudo4"))
            .body("password", is("mdp4"))
            .body("email", is("email4@gmail.com"))
            .body("program", is("64779939a753eb2cbf0abb4f"));
    }
}