package data;

import com.github.javafaker.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import com.github.javafaker.Faker;

import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));
    private DataHelper() {
    }

    @Value
    public static class approvedCardInfo {
        private String cardHolder;
        private String month;
        private String year;
        private String cvv;
        private String cardNumber;
    }
    @Value
    public static class declinedCardInfo {
        private String cardHolder;
        private String month;
        private String year;
        private String cvv;
        private String cardNumber;
    }

    public static String generateCardHolderName() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        return firstName + " " + lastName;
    }

    public static String generateMonth(int shiftMonth) {
        return LocalDate.now().plusMonths(shiftMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateCVV() {
        return faker.numerify("###");
    }

    public static String approvedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String declinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static approvedCardInfo generateCardHolder() {
        return new approvedCardInfo(generateCardHolderName(), generateMonth(5), generateYear(), generateCVV(), approvedCardNumber());
    }

    public static declinedCardInfo generateAnotherCardHolder() {
        return new declinedCardInfo(generateCardHolderName(), generateMonth(5), generateYear(), generateCVV(), declinedCardNumber());
    }

}
