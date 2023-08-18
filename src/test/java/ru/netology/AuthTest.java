package ru.netology;

import data.DataHelper;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;


class AuthTest {

    @BeforeEach
    @SneakyThrows
    void setup() {
        open("http://localhost:9999");
    }

    @AfterEach
    @SneakyThrows
    void cleanup() {
        var deleteAuthCodes = "delete from auth_codes;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, deleteAuthCodes);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Check auth")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var user = DataHelper.getAuthInfo();

        var userId = "select id from users where login='vasya';";

        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            var vasyaId = (String) runner.query(conn, userId, new ScalarHandler<>());

            var loginPage = new LoginPage();
            var codePage = loginPage.login(user.getLogin(), user.getPassword());
            var codeSQL = String.format("select code from auth_codes where user_id='%s' order by created desc", vasyaId);
            var code = (String) runner.query(conn, codeSQL, new ScalarHandler<>());
            codePage.sendCode(code);
        }
    }


}