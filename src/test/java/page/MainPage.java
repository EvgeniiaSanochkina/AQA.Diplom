package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buyDebitButton = $$("button").get(0);
    private SelenideElement buyCreditButton = $$("button").get(1);

    public PayPage buyDebit() {
        buyDebitButton.click();
        return new PayPage();
    }
    public PayPage buyCredit() {
        buyCreditButton.click();
        return new PayPage();
    }

}
