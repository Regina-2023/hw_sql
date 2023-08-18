package ru.netology;

import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripleIncorrectAuth {
    @BeforeEach
    @SneakyThrows
    void setup() {
        var deleteAuthCodes = "delete from auth_codes;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, deleteAuthCodes);
        }
    }


    @Test
    @SneakyThrows
    @DisplayName("Check triple incorrect auth")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var codePage = loginPage.login("vasya", "qwerty123");
        codePage.sendCode("123");
        open("http://localhost:9999");
        loginPage.login("vasya", "qwerty123");
        codePage.sendCode("123");
        open("http://localhost:9999");
        loginPage.login("vasya", "qwerty123");
        codePage.sendCode("123");
        open("http://localhost:9999");
        loginPage.login("vasya", "qwerty123");
        codePage.sendCode("123");
        Thread.sleep(2000);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("http://localhost:9999/", currentUrl);
    }
}
