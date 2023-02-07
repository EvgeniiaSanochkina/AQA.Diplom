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
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPayPage {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.validCard(cardInfo);
        payPage.notificationSuccess();
        var expected = DataHelper.getApprovedStatus();
        var actual = DBHelper.getPayStatus() ;
        assertEquals (expected, actual);
        // postgresql тест прошел
        // sql тест прошел
    }
    @Test
    void testBuyUsingApprovedCardWithInvalidMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.approvedCardWithInvalidMonth(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }
    @Test
    void testBuyUsingApprovedCardWithInvalidYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.approvedCardWithInvalidYear(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }
    @Test
    void testBuyUsingApprovedCardWithPastYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.approvedCardWithPastYear(cardInfo);
        payPage.notificationPastYear();
    }
    @Test
    void testBuyUsingApprovedCardWithRusCardHolder() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithRusCardHolder();
        payPage.approvedCardWithRusCardHolder(cardInfo);
        payPage.notificationInvalidString();
    }
    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithCardHolderWithNumbers() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderWithNumbers();
        payPage.approvedCardWithCardHolderWithNumbers(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyUsingApprovedCardWithCardHolderNameOnly() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderNameOnly();
        payPage.approvedCardWithCardHolderNameOnly(cardInfo);
        payPage.notificationInvalidString();
    }
    //не прошел тест
    @Test
    void testBuyUsingApprovedCardWithInvalidCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.approvedCardWithInvalidCVV(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.approvedCardWithEmptyMonth(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.approvedCardWithEmptyYear(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyCardHolder() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.approvedCardWithEmptyCardHolder(cardInfo);
        payPage.notificationEmptyCardHolder();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.approvedCardWithEmptyCVV(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }
    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals (expected, actual);
        //sql прошел тест
        //postgresql прошел тест
    }
    @Test
    void testBuyUsingCardWithRandomNumber() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.randomCard(cardInfo);
        payPage.notificationError();
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus() ;
        assertEquals (expected, actual);
        //postgresql прошел тест
        //sql прошел тест
    }
    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.validCard(cardInfo);
        payPage.notificationSuccess();
        //postgresql прошел тест
        //sql прошел тест
    }
    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.validCard(cardInfo);
        var expected = DataHelper.getApprovedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals (expected, actual);
        //postgresql не прошел тест
        //sql не прошел тест
    }
    @Test
    void testBuyCreditUsingApprovedCardWithInvalidMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.approvedCardWithInvalidMonth(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithInvalidYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.approvedCardWithInvalidYear(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithPastYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.approvedCardWithPastYear(cardInfo);
        payPage.notificationPastYear();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCardHolder() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.approvedCardWithEmptyCardHolder(cardInfo);
        payPage.notificationEmptyCardHolder();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithInvalidCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.approvedCardWithInvalidCVV(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.approvedCardWithEmptyMonth(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.approvedCardWithEmptyYear(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.approvedCardWithEmptyCVV(cardInfo);
        payPage.notificationInvalidString();
    }
    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        payPage.notificationError();
        //postgresql не прошел тест
        //sql не прошел тест
    }
    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals (expected, actual);
        //postgresql прошел тест
        // sql прошел тест
    }
    @Test
    void testBuyCreditUsingCardWithRandomNumber() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.randomCard(cardInfo);
        payPage.notificationError();
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals (expected, actual);
        // postgresql прошел тест
        // sql прошел тест
    }
}