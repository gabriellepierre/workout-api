package org.com;

import io.quarkus.test.junit.QuarkusTest;
import org.com.dto.timestamp_dto.UserTimestampDto;
import org.com.dto.user_dto.AddUserDto;
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

}