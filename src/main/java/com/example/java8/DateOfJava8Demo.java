package com.example.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo
 * author：xuyy
 * date：2021/3/1  10:24 上午
 */
public class DateOfJava8Demo {


    public static final String LD = "Europe/Londoo";
    public static final String DJ = "Asia/Tokyo";
    public static final String SH = "Asia/Shanghai";
    public static final String NY = "America/New_York";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);

    public static ThreadLocal<SimpleDateFormat> threadSdf1 = new ThreadLocal<>();
    public static ThreadLocal<SimpleDateFormat> threadSdf2 = new ThreadLocal<SimpleDateFormat>() {

        @Override
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat(yyyyMMddHHmmss);
        }
    };

    public static volatile AtomicInteger count = new AtomicInteger();

    public static final DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY)
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR)
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE)
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND)
            .toFormatter();


    public static SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = threadSdf1.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(yyyyMMddHHmmss);
            System.out.println("null" + count.incrementAndGet());

            threadSdf1.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static void main(String[] args) {

        System.out.println(LocalDate.now().with( t->t.plus(1,ChronoField.YEAR.getBaseUnit())));

        LocalDate day1 = LocalDate.of(2020, 11, 12);
        LocalDate day2 = LocalDate.of(2020, 10, 1);
        System.out.println(Period.between(day2,day1).getDays());
        System.out.println(Period.between(day2,day1) );
        System.out.println(ChronoUnit.DAYS.between(day2, day1));

    }

    private static void testPlusDatetime() {
        //java8-
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
        System.out.println(calendar.getTime());
        //java8+
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(1);
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusDays(1);
        System.out.println(zonedDateTime);

    }


    /**
     * SimpleDateFormat 线程不安全
     */
    private static void testSimpleFormatOfThreadSafe() {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        //System.out.println(sdf.parse("2020-11-12 11:02:03"));
                        System.out.println(getSimpleDateFormat().parse("2020-11-12 11:02:03"));
                        System.out.println(threadSdf2.get().parse("2020-11-12 11:02:03"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        threadPool.shutdown();
        boolean b = false;
        try {
            b = threadPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("is stop ? " + b);
    }

    private static void testDateOfJava8() {
        ZoneId bj = ZoneId.systemDefault();
        ZoneId ny = ZoneId.of(NY);
        ZoneId eight = ZoneOffset.ofHours(8);
        System.out.println(eight);

        String stringDate = "2020-03-01 12:01:02";

        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime bjof = ZonedDateTime.of(LocalDateTime.parse(stringDate, dft), bj);
        System.out.println(bjof);
        ZonedDateTime nyof = ZonedDateTime.of(LocalDateTime.parse(stringDate, dft), ny);
        System.out.println(nyof);
        System.out.println(bjof.toLocalDateTime());
        ZonedDateTime eiof = ZonedDateTime.of(LocalDateTime.parse(stringDate, dft), eight);
        System.out.println(eiof);
    }


    /**
     * 一、对于同一个时间字符串表示，不同时区的人得到的时间不一致
     * 格式化时间  改变时区 UTC的意义
     *
     * @throws ParseException
     */
    private static void testDateOfDiffTimeZone() throws ParseException {
        //默认时区
        String stringDate = "2020-03-01 12:01:02";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(stringDate);
        System.out.println(parse + " : " + parse.getTime());

        //改变时区
        sdf.setTimeZone(TimeZone.getTimeZone(DJ));
        Date parse1 = sdf.parse(stringDate);
        System.out.println(parse1 + " : " + parse1.getTime());
    }

    /**
     * 二、格式化后出现的错乱，同一个date，不同的时区的人格式化，得到的时间不一样
     *
     * @throws ParseException
     */
    private static void testDateStringOfDiffTimeZone() throws ParseException {
        //默认时区
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        String format = sdf.format(date);
        System.out.println(format);

        sdf.setTimeZone(TimeZone.getTimeZone(NY));
        String format1 = sdf.format(date);
        System.out.printf(format1);
    }

    /**
     * 带有时区的时间Calendar
     */
    public static void testDateTimeOfTimeZone() {
        Calendar instance = Calendar.getInstance();
        instance.set(2019, 12, 1, 1, 1, 1);
        System.out.println(instance.getTime());

        Calendar instance1 = Calendar.getInstance(TimeZone.getTimeZone(DJ));
        instance1.set(2019, Calendar.DECEMBER, 1, 1, 1, 1);
        System.out.println(instance1.getTime());
    }
}
