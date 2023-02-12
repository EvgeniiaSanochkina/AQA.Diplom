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

    public static Card generateApprovedCardWithValidInformation() {
        return new Card(approvedCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static Card generateDeclinedCardWithValidInformation() {
        return new Card(declinedCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static Card generateCardWithRandomNumber() {
        return new Card(generateRandomCardNumber(), generateMonth(0), generateYear(3), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithInvalidMonth() {
        return new Card(approvedCardNumber(), generateMonth(12), generateYear(0), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithMonth00() {
        return new Card(approvedCardNumber(), generateMonth00(), generateYear(0), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithInvalidYear() {
        return new Card(approvedCardNumber(), generateMonth(0), generateYear(10), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithPastYear() {
        return new Card(approvedCardNumber(), generateMonth(0), generateYear(-1), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithEmptyCardHolder() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateEmptyString(), generateCVV());
    }
    public static Card generateApprovedCardWithCardHolderNameOnly() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderWithNameOnly(), generateCVV());
    }
    public static Card generateApprovedCardWithRusCardHolder() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateRusCardHolderName(), generateCVV());
    }
    public static Card generateApprovedCardWithCardHolderWithNumbers() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderNameWithNumbers(), generateCVV());
    }
    public static Card generateApprovedCardWithLongCardHolderName() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateLongCardHolderNameUsing65Letters(), generateCVV());
    }

    public static Card generateApprovedCardWithInvalidCVV() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateInvalidCVV());
    }

    public static Card generateApprovedCardWithEmptyMonth() {
        return new Card (approvedCardNumber(), generateEmptyString(), generateYear(1), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithEmptyYear() {
        return new Card (approvedCardNumber(), generateMonth(0), generateEmptyString(), generateCardHolderName(), generateCVV());
    }

    public static Card generateApprovedCardWithEmptyCVV() {
        return new Card (approvedCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateEmptyString());
    }

    public static Card generateCardWithIncompleteCardNumber() {
        return new Card (incompleteCardNumber(), generateMonth(0), generateYear(1), generateCardHolderName(), generateCVV());
    }

}
