package com.ieening;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class TestDateTime {
    @Test
    public void testTimeZoneGetDefault() {
        assertTrue("Asia/Shanghai".equals(TimeZone.getDefault().getID()));
    }

    @Test
    public void testTimeZoneSetDefault() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
        assertTrue("GMT+08:00".equals(TimeZone.getDefault().getID()));
    }

    @Test
    public void testLocale() {
        Locale locale = Locale.getDefault();
        assertTrue("zh_CN".equals(locale.toString()));
    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1709045743587L);
        assertTrue("year: 2024 month: 1 day: 27 hour: 22 minute: 55 second: 43 millisecond: 587 day_of_week: 3"
                .equals("year: " + calendar.get(Calendar.YEAR) + " month: " + calendar.get(Calendar.MONTH) + " day: "
                        + calendar.get(Calendar.DAY_OF_MONTH) + " hour: " + calendar.get(Calendar.HOUR_OF_DAY)
                        + " minute: "
                        + calendar.get(Calendar.MINUTE) + " second: " + calendar.get(Calendar.SECOND) + " millisecond: "
                        + calendar.get(Calendar.MILLISECOND) + " day_of_week: " + calendar.get(Calendar.DAY_OF_WEEK)));

    }

    @Test
    public void testDateFormat() {
        Calendar calendar = Calendar.getInstance();
        // 2016-08-15 14:15:20
        calendar.set(2016, 07, 15, 14, 15, 20);
        assertTrue("2016年8月15日 14:15:20".equals(DateFormat.getDateTimeInstance().format(calendar.getTime())));
        assertTrue("2016年8月15日".equals(DateFormat.getDateInstance().format(calendar.getTime())));
        assertTrue("14:15:20".equals(DateFormat.getTimeInstance().format(calendar.getTime())));
    }

    @Test
    public void testDataFormatStyleLocale() {
        Calendar calendar = Calendar.getInstance();
        // 2016-08-15 14:15:20
        calendar.set(2016, 07, 15, 14, 15, 20);
        assertTrue("2016年8月15日 CST 14:15:20".equals(DateFormat
                .getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE).format(calendar.getTime())));
    }

    @Test
    public void testSimpleDateFormatFormat() {
        Calendar calendar = Calendar.getInstance();
        // 2016-08-15 14:15:20
        calendar.set(2016, 07, 15, 14, 15, 20);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy年MM月dd日 E HH时mm分ss秒");
        assertTrue("2016年08月15日 周一 14时15分20秒".equals(sdf.format(calendar.getTime())));
    }

    @Test
    public void testSimpleDateFormatParse() throws ParseException {
        String str = "2016-08-15 14:15:20.456";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date date = sdf.parse(str);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d h:m:s.S a");
            System.out.println(sdf2.format(date));
            assertTrue("2016年8月15 2:15:20.456 下午".equals(sdf2.format(date)));
        } catch (ParseException e) {
            throw e;
        }
    }

    @Test
    public void testInstantDate() {
        Instant instant = Instant.ofEpochMilli(1709045743587L);
        // Instant 转为 Date
        Date d = Date.from(instant);
        assertTrue("Tue Feb 27 22:55:43 CST 2024".equals(d.toString()));
        // Date 转为 Instant
        Instant i = d.toInstant();
        assertTrue("2024-02-27T14:55:43.587Z".equals(i.toString()));
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2017, 7, 11, 20, 45, 5);
        assertTrue("2017-07-11T20:45:05".equals(ldt.toString()));
    }

    @Test
    public void testDateTimeFormatterFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.of(2016, 8, 18, 14, 20, 45);
        assertTrue("2016-08-18 14:20:45".equals(formatter.format(ldt)));
    }

    @Test
    public void testDateTimeFormatterParse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "2016-08-18 14:20:45";
        LocalDateTime ldt = LocalDateTime.parse(str, formatter);
        assertTrue("2016-08-18T14:20:45".equals(ldt.toString()));
    }

    @Test
    public void testTemporalAdjusters() {
        LocalDateTime ldt = LocalDateTime.of(2017, 7, 11, 20, 45, 5);
        // 下周二上午 10 点整
        LocalDateTime nextThu = ldt.with(TemporalAdjusters.next(
                DayOfWeek.TUESDAY)).toLocalDate().atTime(10, 0);
        assertTrue("2017-07-18T10:00".equals(nextThu.toString()));
        // 本月最后一天最后一刻
        LocalDateTime thisMonthLastDayLastTime = ldt.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate()
                .atTime(LocalTime.MAX);
        assertTrue("2017-07-31T23:59:59.999999999".equals(thisMonthLastDayLastTime.toString()));
        long maxDayOfMonth = ldt.range(
                ChronoField.DAY_OF_MONTH).getMaximum();
        LocalDateTime thisMonthLastDayLastTime2 = ldt.withDayOfMonth((int) maxDayOfMonth).toLocalDate()
                .atTime(LocalTime.MAX);
        assertTrue("2017-07-31T23:59:59.999999999".equals(thisMonthLastDayLastTime2.toString()));
        // 下个月第一个周一的下午5点整
        LocalDateTime nextMonthFirstMondaySeventeen = ldt.plusMonths(1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).toLocalDate().atTime(17, 0);
        assertTrue("2017-08-07T17:00".equals(nextMonthFirstMondaySeventeen.toString()));
    }

    @Test
    public void testPeriod() {
        LocalDate ld1 = LocalDate.of(2016, 3, 24);
        LocalDate ld2 = LocalDate.of(2017, 7, 12);
        Period period = Period.between(ld1, ld2);
        assertTrue("1年3月18天".equals(period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天"));
    }

    @Test
    public void testDuration() {
        long lateMinutes = Duration.between(LocalTime.of(9, 0), LocalDateTime.of(2017, 7, 11, 20, 45, 5)).toMinutes();
        assertTrue(705 == lateMinutes);
    }
}