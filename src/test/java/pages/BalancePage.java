package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BalancePage {
    private SelenideElement heading = $("h2[data-test-id='dashboard']");

    public BalancePage() {
        heading.shouldBe(Condition.visible);
    }
}
