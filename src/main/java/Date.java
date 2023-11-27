import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    public static String date(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(daysToAdd);
        return futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
