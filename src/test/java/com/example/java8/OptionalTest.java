package com.example.java8;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * todo
 * author：xuyy
 * date：2021/2/7  3:40 下午
 */
@Slf4j
public class OptionalTest {

    @Test
    public void emptyTest() throws Throwable {
        Date date = trimTimeFieldsToZeroOfNextDay();
        System.out.println(date);
    }


    // 设置成protected而非private是为了方便写单元测试
    protected Date trimTimeFieldsToZeroOfNextDay() {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.add(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 除了基本类型int、long使用== 比较，其他包装类都必须使用equal比较
     *
     * @throws Throwable
     */
    @Test
    public void testEqual() throws Throwable {
        int a = 1111;
        int d = 11111;

        Integer b = 128;
        Integer b1 = 127;
        Integer c = 128;
        Integer c1 = 127;
        System.out.println(a == d);
        System.out.println(a == b);
        System.out.println(b.equals(a));
        System.out.println(c == b);
        System.out.println(c1 == b1);
        System.out.println(b.equals(c));

    }

    @Test
    public void testGenetator() {
        //    long start = System.currentTimeMillis();
        //    List<String> collect = IntStream.rangeClosed(1, 1000 * 10000)
        //            .mapToObj(x -> String.valueOf(x).intern())
        //            .collect(Collectors.toList());
        //    System.out.println("took" + (System.currentTimeMillis() - start));
        //    System.out.println(collect.size());
        String a = IntStream.rangeClosed(1, 1000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining(""))
                + UUID.randomUUID().toString();
        System.out.println(a);
    }

    @Test
    public void testOom() {
        IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
            while (true) {
                //每个线程都是一个死循环
                String a = IntStream.rangeClosed(1, 1000 * 10000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining(""))
                        + UUID.randomUUID().toString();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a.length());

            }
        })).forEach(Thread::start);
    }


    @Test
    public void testDate() throws ParseException {
        String zone = "America/New_York";
        //UTC
        String stringDate = "2020-01-02 22:00:00";
        ZoneId timeOfNY = ZoneId.of(zone);
        ZoneId timeOfSH = ZoneId.of("Asia/Shanghai");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z").withZone(timeOfSH);

        ZonedDateTime of = ZonedDateTime.of(LocalDateTime.parse(stringDate), timeOfNY);
        System.out.println(of);



    }


}

