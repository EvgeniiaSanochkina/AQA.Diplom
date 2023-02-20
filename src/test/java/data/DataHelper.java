package data;

import lombok.Value;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.time.LocalDate;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));
    private static Faker fakerRUS = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String generateCardHolderName() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return firstName + " " + lastName;
    }
    public static String generateCardHolderWithNameOnly() {
        String firstName = faker.name().firstName();
        return firstName;
    }
    public static String generateRusCardHolderName() {
        String firstName = fakerRUS.name().firstName();
        String lastName = fakerRUS.name().lastName();
        return firstName + " " + lastName;
    }
    public static String generateCardHolderNameWithNumbers() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return firstName + " " + lastName + " " + faker.numerify("#");
    }
    public static String generateLongCardHolderNameUsing65Letters() {
        return "DavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavidDavid";
    }
    public static String generateEmptyString() {
        return "";
    }
    public static String generateMonth(int shiftMonth) {
        return LocalDate.now().plusMonths(shiftMonth).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String generateMonth00() {
        return "00";
    }
    public static String generateYear(int shiftYear) {
        return LocalDate.now().plusYears(shiftYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateCVV() {
        return faker.numerify("###");
    }

    public static String generateInvalidCVV() {
        return faker.numerify("##");
    }

    public static String approvedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String declinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String generateRandomCardNumber() {
        String randomCard = faker.numerify("################");
        return randomCard;
    }

    public static String incompleteCardNumber() {
        return "4444 4444 4444 444";
    }

    public static String getApprovedStatus() {
        String status = "APPROVED";
        return status;
    }

    public static String getDeclinedStatus() {
        String status = "DECLINED";
        return status;
    }

    public static CardPattern generateApprovedCardWithValidInformation() {
        return new CardPattern(approvedCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateDeclinedCardWithValidInformation() {
        return new CardPattern(declinedCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateCardWithRandomNumber() {
        return new CardPattern(generateRandomCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithInvalidMonth() {
        return new CardPattern(approvedCardNumber(), generateMonth(12), generateYear(0), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithMonth00() {
        return new CardPattern(approvedCardNumber(), generateMonth00(), generateYear(0), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithInvalidYear() {
        return new CardPattern(approvedCardNumber(), generateMonth(0), generateYear(10), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithPastYear() {
        return new CardPattern(approvedCardNumber(), generateMonth(0), generateYear(-1), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithEmptyCardHolder() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateEmptyString(), generateCVV());
    }
    public static CardPattern generateApprovedCardWithCardHolderNameOnly() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderWithNameOnly(), generateCVV());
    }
    public static CardPattern generateApprovedCardWithRusCardHolder() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateRusCardHolderName(), generateCVV());
    }
    public static CardPattern generateApprovedCardWithCardHolderWithNumbers() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderNameWithNumbers(), generateCVV());
    }
    public static CardPattern generateApprovedCardWithLongCardHolderName() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateLongCardHolderNameUsing65Letters(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithInvalidCVV() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateInvalidCVV());
    }

    public static CardPattern generateApprovedCardWithEmptyMonth() {
        return new CardPattern (approvedCardNumber(), generateEmptyString(), generateYear(1), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithEmptyYear() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateEmptyString(), generateCardHolderName(), generateCVV());
    }

    public static CardPattern generateApprovedCardWithEmptyCVV() {
        return new CardPattern (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateEmptyString());
    }

    public static CardPattern generateCardWithIncompleteCardNumber() {
        return new CardPattern (incompleteCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateCVV());
    }

}
