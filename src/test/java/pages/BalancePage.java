package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BalancePage {
    private SelenideElement tagH2 = $("h2");

    public BalancePage() {
        tagH2.shouldBe(Condition.visible);
    }
}
