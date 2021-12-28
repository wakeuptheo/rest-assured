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
    void registration() {
        step("Register new user", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .body("__RequestVerificationToken=yharMk0UzLqakit" +
                            "-MPnBjOJc6oyYNnsn8u_CWOwbVk9djHYwKWUFoxe" +
                            "0_2nndWrMgtJaual3dxIZ6SkidpixmRpzv6NucA-" +
                            "EDDWc0k5Wmrk1&Gender=M&FirstName=Alex&La" +
                            "stName=Qwerty&Email=qwerty%40www.co&Pass" +
                            "word=123456&ConfirmPassword=123456&regis" +
                            "ter-button=Register")
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
    void addItemToShoppingCart() {
        step("Add an item with custom specs to the Shopping Cart", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body("product_attribute_74_5_26=82" +
                            "&product_attribute_74_6_27=85" +
                            "&product_attribute_74_3_28=87" +
                            "&product_attribute_74_8_29=88" +
                            "&product_attribute_74_8_29=89" +
                            "&product_attribute_74_8_29=90" +
                            "&addtocart_74.EnteredQuantity=2")
                    .cookie("Nop.customer=69589107-6373-41bd-891d-47fb44277adc;")
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
    void leaveFeedback() {
        step("Fill the contact form and send feedback", () -> {
            given()
                    .filter(customLogFilter().withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .body("FullName=Alex+Qwerty&Email=qwerty%40www.co" +
                            "&Enquiry=Test+enquiry&send-email=Submit")
                    .cookie("Nop.customer=69589107-6373-41bd-891d-47fb44277adc;")
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






