package test;

import data.DBHelper;
import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPayPageSQL {

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
        // тест прошел
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
    void testBuyUsingApprovedCardWithInvalidCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.approvedCardWithInvalidCVV(cardInfo);
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.approvedCardWithEmptyMonth(cardInfo);
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyUsingApprovedCardWithEmptyYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.approvedCardWithEmptyYear(cardInfo);
        payPage.notificationEmptyString();
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
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        payPage.notificationError();
        //не прошел тест
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
        //прошел тест
        //постгрескл не прошел
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
        //прошел тест
    }
    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.validCard(cardInfo);
        payPage.notificationSuccess();
        //прошел тест
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
        //не прошел тест
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
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyMonth() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.approvedCardWithEmptyMonth(cardInfo);
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyYear() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.approvedCardWithEmptyYear(cardInfo);
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCVV() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.approvedCardWithEmptyCVV(cardInfo);
        payPage.notificationEmptyString();
    }
    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        open("http://localhost:8080/");
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.declinedCardWithValidInformation(cardInfo);
        payPage.notificationError();
        //не прошел тест
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
        //прошел тест
        // не прошел тест
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
        // прошел тест
    }
}