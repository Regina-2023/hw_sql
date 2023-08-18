package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CodePage {
    private SelenideElement codeField = $("input[name='code']");
    private SelenideElement submitField = $("span[class='button__text']");

    public CodePage() {
        codeField.shouldBe(Condition.visible);
    }

    public BalancePage sendCode(String code) {
        codeField.setValue(code);
        submitField.click();
        return new BalancePage();

    }
}
