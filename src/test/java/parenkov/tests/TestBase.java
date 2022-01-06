package parenkov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import parenkov.config.App;

import java.io.InputStream;

public class TestBase {
    @BeforeAll
    static void testConfiguration() {
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        Configuration.startMaximized = true;
    }

    public String request(int sheet, int row, int cell) throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("request.xlsx")) {
            XLS parsed = new XLS(stream);
            String body = parsed.excel.getSheetAt(sheet).getRow(row).getCell(cell).getStringCellValue();
            return body;
        }
    }
}