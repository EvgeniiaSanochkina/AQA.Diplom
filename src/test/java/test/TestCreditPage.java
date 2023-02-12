package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.TestPayPage.localhost;

public class TestCreditPage {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeAll
    static void openLocalHost() {
        open("http://localhost:8080/");
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @AfterAll
    static void tearDown() {
        cleanDataBase();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationSuccess();
        //postgresql прошел тест
        //sql прошел тест
    }

    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        var expected = DataHelper.getApprovedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals(expected, actual);
        //postgresql не прошел тест
        //sql не прошел тест
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidMonth() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidYear() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithPastYear() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.card(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCardHolder() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.card(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidCVV() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyMonth() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyYear() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCVV() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals(expected, actual);
        //postgresql не прошел тест
        // sql не прошел тест
    }

    @Test
    void testBuyCreditUsingCardWithRandomNumberAndReturnErrorNotification() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.card(cardInfo);
        payPage.notificationError();
        // postgresql прошел тест
        // sql прошел тест
    }

    @Test
    void testBuyCreditUsingCardWithRandomNumberAndReturnDeclinedStatus() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.card(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals(expected, actual);
        // не прошел тест

    }

    @Test
    void testBuyUsingCardWithIncompleteNumber() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        open(localhost);
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }
}
