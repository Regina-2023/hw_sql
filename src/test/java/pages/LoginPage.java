package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("input[name='login']");
    private SelenideElement passwordField = $("input[name='password']");
    private SelenideElement submitField = $("span[class='button__text']");

    public CodePage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitField.click();
        return new CodePage();

    }

}
