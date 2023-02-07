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

    @Value
    public static class approvedCardWithValidInformation {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class randomCardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithInvalidMonth {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithInvalidYear {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithPastYear {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithEmptyCardHolder {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }
    @Value
    public static class approvedCardWithRusCardHolder {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }
    @Value
    public static class approvedCardWithCardHolderNameOnly {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }
    @Value
    public static class approvedCardWithCardHolderWithNumbers {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithInvalidCVV {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithEmptyMonth {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class approvedCardWithEmptyYear {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }
    @Value
    public static class approvedCardWithEmptyCVV {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    @Value
    public static class declinedCardWithValidInformation {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
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

    public static String generateEmptyString() {
        return null;
    }

    public static String generateMonth(int shiftMonth) {
        return LocalDate.now().plusMonths(shiftMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateInvalidMonth() {
        int invalidMonthInt = faker.number().numberBetween(13, 99);
        String invalidMonth = String.valueOf(invalidMonthInt);
        return invalidMonth;
    }

    public static String generateYear() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidYear() {
        int invalidYearInt = faker.number().numberBetween(28, 99);
        String invalidYear = String.valueOf(invalidYearInt);
        return invalidYear;
    }

    public static String generatePastYear(int shiftYear) {
        return LocalDate.now().minusYears(shiftYear).format(DateTimeFormatter.ofPattern("yy"));
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

    public static String getApprovedStatus() {
        String status = "APPROVED";
        return status;
    }

    public static String getDeclinedStatus() {
        String status = "DECLINED";
        return status;
    }

    public static approvedCardWithValidInformation generateApprovedCardWithValidInformation() {
        return new approvedCardWithValidInformation(approvedCardNumber(), generateMonth(5), generateYear(), generateCardHolderName(), generateCVV());
    }

    public static declinedCardWithValidInformation generateDeclinedCardWithValidInformation() {
        return new declinedCardWithValidInformation(declinedCardNumber(), generateMonth(5), generateYear(), generateCardHolderName(), generateCVV());
    }

    public static randomCardInfo generateCardWithRandomNumber() {
        return new randomCardInfo(generateRandomCardNumber(), generateMonth(5), generateYear(), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithInvalidMonth generateApprovedCardWithInvalidMonth() {
        return new approvedCardWithInvalidMonth(approvedCardNumber(), generateInvalidMonth(), generateYear(), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithInvalidYear generateApprovedCardWithInvalidYear() {
        return new approvedCardWithInvalidYear(approvedCardNumber(), generateMonth(5), generateInvalidYear(), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithPastYear generateApprovedCardWithPastYear() {
        return new approvedCardWithPastYear(approvedCardNumber(), generateMonth(5), generatePastYear(1), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithEmptyCardHolder generateApprovedCardWithEmptyCardHolder() {
        return new approvedCardWithEmptyCardHolder(approvedCardNumber(), generateMonth(5), generateYear(), generateEmptyString(), generateCVV());
    }
    public static approvedCardWithCardHolderNameOnly generateApprovedCardWithCardHolderNameOnly() {
        return new approvedCardWithCardHolderNameOnly(approvedCardNumber(), generateMonth(5), generateYear(), generateCardHolderWithNameOnly(), generateCVV());
    }
    public static approvedCardWithRusCardHolder generateApprovedCardWithRusCardHolder() {
        return new approvedCardWithRusCardHolder(approvedCardNumber(), generateMonth(5), generateYear(), generateRusCardHolderName(), generateCVV());
    }
    public static approvedCardWithCardHolderWithNumbers generateApprovedCardWithCardHolderWithNumbers() {
        return new approvedCardWithCardHolderWithNumbers(approvedCardNumber(), generateMonth(5), generateYear(), generateCardHolderNameWithNumbers(), generateCVV());
    }

    public static approvedCardWithInvalidCVV generateApprovedCardWithInvalidCVV() {
        return new approvedCardWithInvalidCVV(approvedCardNumber(), generateMonth(5), generateYear(), generateCardHolderName(), generateInvalidCVV());
    }

    public static approvedCardWithEmptyMonth generateApprovedCardWithEmptyMonth() {
        return new approvedCardWithEmptyMonth(approvedCardNumber(), generateEmptyString(), generateYear(), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithEmptyYear generateApprovedCardWithEmptyYear() {
        return new approvedCardWithEmptyYear(approvedCardNumber(), generateMonth(5), generateEmptyString(), generateCardHolderName(), generateCVV());
    }

    public static approvedCardWithEmptyCVV generateApprovedCardWithEmptyCVV() {
        return new approvedCardWithEmptyCVV(approvedCardNumber(), generateMonth(5), generateYear(), generateCardHolderName(), generateEmptyString());
    }

}
