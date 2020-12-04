package com.oop.lobovam;

public class Date {

    public static final int MIN_YEAR = -1;
    public static final int MAX_YEAR = 10000;

    private int day;
    private int month;
    private int year;

    public enum Weekday
    {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    public Date() {
        day = 15;
        month = 10;
        year = 1582;
    }

    public Date(int day, int month, int year) {
        if(isYearValid(year)) {
            throw new IllegalArgumentException("Year should be more than 1000, but less than 10000");
        }
        this.year = year;
        if(isMonthValid(month)) {
            throw new IllegalArgumentException("Month should be more than 0 but less than 13");
        }
        this.month = month;
        if(isDayValid(day, month, year)) {
            throw new IllegalArgumentException("This day does not exist!");
        }
        this.day = day;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if(year % 100 != 0) {
            return true;
        } else return year % 400 == 0;
    }

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    public void setDay(int day) {
        if(isDayValid(day, month, year)) {
            throw new IllegalArgumentException("This day does not exist!");
        }
        this.day = day;
    }

    public void setMonth(int month) {
        if(isMonthValid(month)) {
            throw new IllegalArgumentException("Month should be more than 0 but less than 13");
        }
        this.month = month;
    }

    public void setYear(int year) {
        if(isYearValid(year)){
            throw new IllegalArgumentException("Year should be more than 1000, but less than 10000");
        }
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    private boolean isYearValid(int year) {
        return (year <= MIN_YEAR) || (year >= MAX_YEAR);
    }

    private boolean isMonthValid(int month) {
        return (month < 0) || (month > 12);
    }

    private boolean isDayValid(int day, int month, int year)
    {
        return ((day < 0) || ((month != 1 || day > 31) &&
                (isLeapYear(year) ? (month != 2 || day > 29) : (month != 2 || day > 28)) &&
                (month != 3 || day > 31) &&
                (month != 4 || day > 30) &&
                (month != 5 || day > 31) &&
                (month != 6 || day > 30) &&
                (month != 7 || day > 31) &&
                (month != 8 || day > 31) &&
                (month != 9 || day > 30) &&
                (month != 10 || day > 31) &&
                (month != 11 || day > 30) &&
                (month != 12 || day > 31)));
    }

    private int weekdayNumber () {
        int m = this.month;
        int d= this.day;
        int y = this.year;
        if (m < 3) {
            --y;
            m+= 10;
        } else
            m -= 2;
        return ((d + 31 * m / 12 + y + y / 4 - y / 100 + y / 400) % 7);
    }

    public Weekday weekday() {
        int dayOfWeek = weekdayNumber();
        return Weekday.values()[dayOfWeek];
    }
}
