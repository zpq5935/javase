package jdk8.doc.datetime;

import org.junit.Test;

import java.time.*;
import java.util.Objects;
import java.util.stream.IntStream;

public class DateTimeTest {

    public static void main(String[] args) {
    }

    @Test
    public void t1(){
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(100);
    }

    @Test
    public void testMyTemporalAdjuster(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.with(new MyTemporalAdjusters()));
    }

    @Test
    public void testPeriod(){
        Period period = Period.between(LocalDate.ofYearDay(2000, 22), LocalDate.now());
        System.out.println(String.format("%d years\t%d months\t%d days", period.getYears(), period.getMonths(), period.getDays()));
    }

    @Test
    public void testPrint(){
        bothFifteenAndFridayInYearMonths(2018);
    }

    // 打印一年中 既是周五又是每月15号的日期
    public static void bothFifteenAndFridayInYearMonths(int yearInt){
        int months = 12, monthDay = 15;

        IntStream.rangeClosed(1, months).forEach(month -> {
            LocalDate date = LocalDate.of(yearInt, month, monthDay);
            if(Objects.equals(date.getDayOfWeek(),DayOfWeek.FRIDAY)){
                System.out.println(date);
            }
        });
    }
}
