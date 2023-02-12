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


public class TestPayPage {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    //@BeforeEach
    //static void openLocalHost() {
    //  open("http://localhost:8080/");
    //}
    public static String localhost = "http://localhost:8080/";

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void tearDown() {
        cleanDataBase();
    }

    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationSuccess();
    }

    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        open(localhost);
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
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidYear() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithPastYear() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.card(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyUsingApprovedCardWithRusCardHolder() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithRusCardHolder();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithCardHolderWithNumbers() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderWithNumbers();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithCardHolderNameOnly() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderNameOnly();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithInvalidCVV() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyMonth() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyYear() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCardHolder() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.card(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCVV() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.card(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        open(localhost);
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
        open(localhost);
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
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        open(localhost);
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.card(cardInfo);
        payPage.notificationInvalidString();
    }
}