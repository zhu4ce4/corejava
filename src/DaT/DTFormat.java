package DaT;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class DTFormat {
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.of(2019, 1, 1, 1, 1, 1, 0, ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String formatted = formatter.format(zdt);
        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.CHINA).format(zdt);
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
        formatted = formatter.format(zdt);
        System.out.println(formatted);

        LocalDate birth = LocalDate.parse("1988-08-08");
        System.out.println(birth);

        LocalDateTime zdt2 = LocalDateTime.parse("1988-08-08 09:19:19", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(zdt2);

        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.print(day.getDisplayName(TextStyle.SHORT, Locale.CHINA) + " ");
        }
    }
}
