package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CardPattern;
import data.DataHelper;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {
    private SelenideElement cardNumber = $$("input").get(0);
    private SelenideElement month = $$("input").get(1);
    private SelenideElement year = $$("input").get(2);
    private SelenideElement cardHolder = $$("input").get(3);
    private SelenideElement cvc = $$("input").get(4);
    private SelenideElement continueButton = $$("button").get(2);
    private SelenideElement invalidExpirationDate = $(withText("Неверно указан срок действия карты"));
    private SelenideElement pastYear = $(withText("Истёк срок действия карты"));
    private SelenideElement emptyCardHolder = $(withText("Поле обязательно для заполнения"));
    private SelenideElement invalidString = $(withText("Неверный формат"));
    private SelenideElement notificationSuccess = $(By.className("notification_status_ok"));
    private SelenideElement notificationError = $(By.className("notification_status_error"));

    public void notificationSuccess() {
        notificationSuccess.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
    public void notificationError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
    public void notificationInvalidExpirationDate() { invalidExpirationDate.shouldBe(Condition.visible); }
    public void notificationPastYear() {
        pastYear.shouldBe(Condition.visible);
    }
    public void notificationEmptyCardHolder() {
        emptyCardHolder.shouldBe(Condition.visible);
    }
    public void notificationInvalidString() { invalidString.shouldBe(Condition.visible); }

    public void card (CardPattern cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        cardHolder.setValue(cardInfo.getCardHolder());
        cvc.setValue(cardInfo.getCvv());
        continueButton.click();
    }

}
