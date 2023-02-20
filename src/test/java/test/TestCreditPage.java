package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCreditPage {
    @BeforeEach
    public void openPage() {
        open("http://localhost:8080/");
    }
    @BeforeAll
    public void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    public void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void tearDown() {
        cleanDataBase();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationSuccess();
        //postgresql прошел тест
        //sql прошел тест
    }

    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
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
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithPastYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.card(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCardHolder() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.card(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidCVV() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyMonth() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCVV() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
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
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.card(cardInfo);
        payPage.notificationError();
        // postgresql прошел тест
        // sql прошел тест
    }

    @Test
    void testBuyCreditUsingCardWithRandomNumberAndReturnDeclinedStatus() {
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
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }
}
