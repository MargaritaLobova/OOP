package com.oop.lobovam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

    Calendar calendar = new Calendar();

    //1) Какой день недели будет через 1024 дня?
    @Test
    void Weekday1024DaysOver() {
        Date date = new Date(30, 11, 2020);
        date = calendar.addTime(1024, date, "d");
        Date.Weekday actual = date.weekday();
        assertEquals(Date.Weekday.WEDNESDAY, actual);
    }

    //2) Сколько лет, месяцев и дней назад был день победы 9 мая 1945 года?
    @Test
    void VictoryDay() {
        Date current = new Date(30, 11, 2020);
        Date victoryDay = new Date(9, 5, 1945);
        calendar.subDates(current, victoryDay);
    }

    //3) В какой день недели вы родились?
    @Test
    void birthdayTest() {
        Date birthday = new Date(20, 8, 2002);
        Date.Weekday actual = birthday.weekday();
        assertEquals(Date.Weekday.TUESDAY, actual);
    }

    //4) Какой месяц будет через 17 недель?
    @Test
    void month17WeeksOver() {
        Date date = new Date(30, 11, 2020);
        date = calendar.addTime(17*7, date, "d");
        System.out.println("After 17 weeks it will be month №" + date.getMonth());
        assertEquals(3, date.getMonth());
    }

    //5) Сколько дней до нового года?
    @Test
    void daysUntilXmas() {
        Date xmas = new Date(1, 1, 2021);
        Date today = new Date(30, 11, 2020);
        calendar.subDates(xmas, today);
    }

    //6) Ближайшая пятница 13-го числа месяца?
    @Test
    void friday13() {
        Date date = new Date(30, 11, 2020);
        while(date.getDay() != 13 ) {
            date = calendar.addTime(1, date, "d");
        }
        while(date.weekday()!= Date.Weekday.FRIDAY) {
            date = calendar.addTime(1, date, "m");
        }

        System.out.println(date.getDay());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
    }

    /*------  COMMON TESTS  ------*/

    //Todo remove to DateTest.java
    @Test
    void weekdayTest() {
        Date date = new Date(12, 3, 2020);
        Date.Weekday actual = date.weekday();
        assertEquals(Date.Weekday.THURSDAY, actual);
        date.setDate(29, 2, 2020 );
        actual = date.weekday();
        assertEquals(Date.Weekday.SATURDAY, actual);
    }

    @Test
    void addTest() {
        Date actual = new Date(12, 3, 2020);
        actual = calendar.addTime(1234, actual, "y");
        Date expected = new Date(12, 3, 3254);
        assertEquals(expected.getClass(), actual.getClass());
        actual = calendar.addTime(12, actual, "m");
        expected = new Date(12, 3, 2021);
        assertEquals(expected.getClass(), actual.getClass());
        expected = new Date(29, 2, 2020);
        actual = calendar.addTime(108, expected, "m");
        expected = new Date(1, 3, 2029);
        assertEquals(expected.getClass(), actual.getClass());
        expected = new Date(29, 2, 2020);
        actual = calendar.addTime(100, expected, "d");
        expected = new Date(8, 6, 2020);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void subTest() {
        Date actual = new Date(12, 3, 2020);
        actual = calendar.subTime(1234, actual, "y");
        Date expected = new Date(12, 3, 786);
        assertEquals(expected.getDay(), actual.getDay());
        assertEquals(expected.getMonth(), actual.getMonth());
        assertEquals(expected.getYear(), actual.getYear());
        actual = calendar.subTime(12, actual, "m");
        expected = new Date(12, 3, 785);
        assertEquals(expected.getDay(), actual.getDay());
        assertEquals(expected.getMonth(), actual.getMonth());
        assertEquals(expected.getYear(), actual.getYear());

        expected = new Date(29, 2, 2020);
        actual = calendar.subTime(108, expected, "m");
        expected = new Date(1, 3, 2011);
        assertEquals(expected.getDay(), actual.getDay());
        assertEquals(expected.getMonth(), actual.getMonth());
        assertEquals(expected.getYear(), actual.getYear());

        expected = new Date(29, 2, 2020);
        actual = calendar.subTime(100, expected, "d");
        expected = new Date(21, 11, 2019);
        assertEquals(expected.getDay(), actual.getDay());
        assertEquals(expected.getMonth(), actual.getMonth());
        assertEquals(expected.getYear(), actual.getYear());
    }

    @Test
    void subDateTest() {
        Date date = new Date(29, 2, 2020);
        Date date1 = new Date(1, 8, 1974);
        calendar.subDates(date, date1);
    }

    /*------  EXCEPTION TEST  ------*/

    @Test
    void addSubExceptionTest() {
        Date date = new Date();
        try {
            calendar.addTime(1024, date, "o");
            fail( "My method didn't throw  IllegalArgumentException when I expected it to" );
        } catch (IllegalArgumentException expectedException) {
            expectedException.printStackTrace();
        }
        try {
            calendar.subTime(1024, date, "o");
            fail( "My method didn't throw  IllegalArgumentException when I expected it to" );
        } catch (IllegalArgumentException expectedException) {
            expectedException.printStackTrace();
        }

    }
}