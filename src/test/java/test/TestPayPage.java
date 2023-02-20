package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;

import io.qameta.allure.selenide.AllureSelenide;
//import lombok.var;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayPage {
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
    void testBuyUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationSuccess();
    }

    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationSuccess();
        var expected = DataHelper.getApprovedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
        // postgresql тест прошел
        // sql тест прошел
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidMonth() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithPastYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.card(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyUsingApprovedCardWithRusCardHolder() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithRusCardHolder();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithCardHolderWithNumbers() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderWithNumbers();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithCardHolderNameOnly() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderNameOnly();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithInvalidCVV() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyMonth() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCardHolder() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.card(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCVV() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
        //не sql прошел тест
        //не postgresql прошел тест
    }

    @Test
    void testBuyUsingCardWithRandomNumber() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.card(cardInfo);
        payPage.notificationError();
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
        //postgresql прошел тест
        //sql прошел тест
    }

    @Test
    void testBuyUsingCardWithIncompleteNumber() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }
}