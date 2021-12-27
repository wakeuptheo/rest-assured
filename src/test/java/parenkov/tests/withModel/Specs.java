package parenkov.tests.withModel;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;

public class Specs {
    public static RequestSpecification baseRequest = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .log().uri()
            .log().headers()
            .log().body();

    public static ResponseSpecification createUserResponse = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification baseSuccessfulResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification unsuccessfulRegistrationResponse = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectBody("error", is("Missing email or username"))
            .build();
}
