package jdk8.doc.datetime;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 自定义时间调节器，返回 日期下一个发薪日期（默认每月15号）
 */
public class MyTemporalAdjusters implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate localDate = LocalDate.from(temporal);

        int dayOfMonth = localDate.getDayOfMonth();
        if (dayOfMonth <= 15) {
            return localDate.withDayOfMonth(15);
        }

        return localDate.plusMonths(1).withDayOfMonth(15);

    }
}
