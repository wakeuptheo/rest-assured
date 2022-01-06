package parenkov.tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static parenkov.filters.CustomLogFilter.customLogFilter;

@Story("Demo Web Shop")
@Owner("Fedor Parenkov")
public class WebShopTests extends TestBase {

    @Test
    @DisplayName("User registration")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Main page", url = "http://demowebshop.tricentis.com")
    void registration() throws Exception {
        String payload = request(0, 1, 1);
        step("Register new user", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .body(payload)
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .log().body()
                    .when()
                    .post("/register")
                    .then()
                    .statusCode(302)
                    .log().status()
                    .log().headers();
        });
    }

    @Test
    @DisplayName("Adding an item to the Shopping Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Main page", url = "http://demowebshop.tricentis.com")
    void addItemToShoppingCart() throws Exception {
        String payload = request(0, 2, 1);
        String cookie = request(0, 2, 2);
        step("Add an item with custom specs to the Shopping Cart", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body(payload)
                    .cookie(cookie)
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .log().body()
                    .when()
                    .post("/addproducttocart/details/74/1")
                    .then()
                    .statusCode(200)
                    .log().status()
                    .log().headers()
                    .log().body()
                    .body("success", is(true))
                    .body("message", is("The product has been added to your " +
                            "<a href=\"/cart\">shopping cart</a>"));
        });
    }

    @Test
    @DisplayName("Sending feedback by 'Contact Us' form")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "Main page", url = "http://demowebshop.tricentis.com")
    void leaveFeedback() throws Exception {
        String payload = request(0, 3, 1);
        String cookie = request(0, 3, 2);
        step("Fill the contact form and send feedback", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .body(payload)
                    .cookie(cookie)
                    .log().method()
                    .log().uri()
                    .log().headers()
                    .log().body()
                    .when()
                    .post("/contactus")
                    .then()
                    .statusCode(200)
                    .log().status()
                    .log().headers();
        });
    }
}






