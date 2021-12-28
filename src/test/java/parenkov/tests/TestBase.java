package parenkov.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import parenkov.config.App;

public class TestBase {
    @BeforeAll
    static void testConfiguration() {
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        Configuration.startMaximized = true;
    }
}
