package ru.netology;

import data.DataHelper;
import data.SQLHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;


class AuthTest {
    @AfterAll
    static void clearDB(){
        SQLHelper.clearDB();
    }
    @BeforeEach
    @SneakyThrows
    void setup() {
        open("http://localhost:9999");
    }

    @AfterEach
    void cleanup() {
        var deleteAuthCodes = "delete from auth_codes;";
        SQLHelper.delete(deleteAuthCodes);
    }

    @Test
    @SneakyThrows
    @DisplayName("Check auth")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var user = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var codePage = loginPage.login(user.getLogin(), user.getPassword());
        var userCode = SQLHelper.getAuthCode();
        codePage.sendCode(userCode);
    }

}