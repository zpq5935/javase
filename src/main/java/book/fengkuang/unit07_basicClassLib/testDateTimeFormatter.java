package book.fengkuang.unit07_basicClassLib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class testDateTimeFormatter {
    public static void main(String[] args) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
				DateTimeFormatter.ISO_DATE_TIME,
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
        };
        LocalDateTime localDateTime = LocalDateTime.now();
        for (int i = 0; i < formatters.length; i++) {
            System.out.println(localDateTime.format(formatters[i]));
        }
    }
}
