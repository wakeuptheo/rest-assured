package parenkov.tests.withModel;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parenkov.models.User;
import parenkov.models.UserSupport;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parenkov.filters.CustomLogFilter.customLogFilter;
import static parenkov.tests.withModel.Specs.baseRequest;
import static parenkov.tests.withModel.Specs.createUserResponse;
import static parenkov.tests.withModel.Specs.baseSuccessfulResponse;
import static parenkov.tests.withModel.Specs.unsuccessfulRegistrationResponse;

@Story("Reqres.in")
public class TestsWithModel {

    @Test
    @DisplayName("New user creation")
    void createUser() {
        step("Create new user", () -> {
            User data = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .spec(baseRequest)
                    .body("{ \"name\": \"Ivan\", \"job\": \"Developer\" }")
                    .when()
                    .post("/users")
                    .then()
                    .spec(createUserResponse)
                    .log().status()
                    .log().body()
                    .extract().as(User.class);
            assertEquals("Ivan", data.getName());
            assertEquals("Developer", data.getJob());
        });
    }

    @Test
    @DisplayName("User info updating")
    void updateUser() {
        step("Update user info", () -> {
            User data = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .spec(baseRequest)
                    .body("{ \"name\": \"Alex\", \"job\": \"QA Engineer\" }")
                    .when()
                    .put("/users/{id}", 10)
                    .then()
                    .spec(baseSuccessfulResponse)
                    .log().status()
                    .log().body()
                    .extract().as(User.class);
            assertEquals("Alex", data.getName());
            assertEquals("QA Engineer", data.getJob());
        });
    }

    @Test
    @DisplayName("Successful user registration")
    void successfulRegistration() {
        step("Sign up new user", () -> {
            User data = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .spec(baseRequest)
                    .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"123\" }")
                    .when()
                    .post("/register")
                    .then()
                    .spec(baseSuccessfulResponse)
                    .log().status()
                    .log().body()
                    .extract().as(User.class);
            assertEquals(4, data.getId());
            assertEquals("QpwL5tke4Pnpja7X4", data.getToken());
        });
    }

    @Test
    @DisplayName("Unsuccessful user registration")
    void unsuccessfulRegistration() {
        step("Sign up new user with unfilled email field", () -> {
            User data = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .spec(baseRequest)
                    .body("{ \"email\": \"\", \"password\": \"123\" }")
                    .when()
                    .post("/register")
                    .then()
                    .spec(unsuccessfulRegistrationResponse)
                    .log().status()
                    .log().body()
                    .extract().as(User.class);
            assertEquals("Missing email or username", data.getError());
        });
    }

    @Test
    @DisplayName("Checking project support message")
    void userSupport() {
        step("Check project support message", () -> {
            UserSupport support = given()
                    .filter(customLogFilter().withCustomTemplates())
                    .spec(baseRequest)
                    .when()
                    .get("/users/10")
                    .then()
                    .spec(baseSuccessfulResponse)
                    .log().status()
                    .log().body()
                    .extract().as(UserSupport.class);
            assertEquals("To keep ReqRes free, contributions towards " +
                    "server costs are appreciated!", support.getSupport().getText());
        });
    }
}
