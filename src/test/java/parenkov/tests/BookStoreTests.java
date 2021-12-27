package parenkov.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static parenkov.filters.CustomLogFilter.customLogFilter;

@Story("Book Store")
public class BookStoreTests extends TestBase {

    Faker faker = new Faker();
    String randomNum = faker.number().digits(4);

    @Test
    @DisplayName("User registration")
    void userRegistration() {
        step("Register new user", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .accept("application/json")
                    .contentType("application/json")
                    .body("{ \"userName\": \"test" + randomNum + "\"," +
                            " \"password\": \"Hv4(c2!Jk\"}")
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .log().body()
                    .when()
                    .post("https://demoqa.com/Account/v1/User")
                    .then()
                    .statusCode(201)
                    .log().status()
                    .log().headers()
                    .log().body()
                    .body("username", is("test" + randomNum))
                    .body("userID", notNullValue());
        });
    }

    @Test
    @DisplayName("User token generation")
    void tokenGenerate() {
        step("Generate user token", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .accept("application/json")
                    .contentType("application/json")
                    .body("{ \"userName\": \"test110\"," +
                            " \"password\": \"Hv4(c2!Jk\"}")
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .log().body()
                    .when()
                    .post("https://demoqa.com/Account/v1/GenerateToken")
                    .then()
                    .statusCode(200)
                    .log().status()
                    .log().headers()
                    .log().body()
                    .body("token", notNullValue())
                    .body("status", is("Success"))
                    .body("result", is("User authorized successfully."));
        });
    }

    @Test
    @DisplayName("Getting book info")
    void getBookInfo() {
        step("Get book info by using ISBN", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .accept("application/json")
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .when()
                    .get("https://demoqa.com/BookStore/v1/Book?ISBN=9781449331818")
                    .then()
                    .statusCode(200)
                    .log().status()
                    .log().headers()
                    .log().body()
                    .body("isbn", is("9781449331818"))
                    .body("title", is("Learning JavaScript Design Patterns"));
        });
    }
}

