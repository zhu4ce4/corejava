package DaT;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDT {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2018, 11, 1);
        LocalDate firsttuesday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        LocalDate firsttuesday2 = today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        /*
        next/previous 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星 期几要求的日期。
        nextOrSame/previousOrSame 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星 期几要求的日期，如果该日期已经符合要求，直接返回该对象。
         */
//        System.out.println(firsttuesday+"："+firsttuesday2);

        LocalDate fourthtuesday = today.with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.TUESDAY));
        LocalDate lasttuesday = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.TUESDAY));
//        System.out.println(fourthtuesday);
//        System.out.println(lasttuesday);

        TemporalAdjuster next_workday = w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };

        LocalDate backToWork = today.with(next_workday);
//        System.out.println(backToWork);

        TemporalAdjuster next_workdaytoo = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() > 5);
            return result;
        });
        LocalDate backToWorktoo = today.with(next_workday);
//        System.out.println(backToWorktoo);

        LocalTime rightNow = LocalTime.now();
        LocalTime bedTime = LocalTime.of(22, 30);
        LocalTime wakeUp = bedTime.plusHours(8);


    }


}
