package com.oop.lobovam;

public class Date {

    final int MIN_YEAR = 0;
    final int MAX_YEAR = 10000;

    int day;
    int month;
    int year;

    enum Weekday
    {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    Date() {
        day = 15;
        month = 10;
        year = 1582;
    }

    Date(int d, int m, int y) {
        if(isYearValid(y)) {
            throw new IllegalArgumentException("Year should be more than 1000, but less than 10000");
        }
        year = y;
        if(isMonthValid(m)) {
            throw new IllegalArgumentException("Month should be more than 0 but less than 13");
        }
        month = m;
        if(isDayValid(d, m, y)) {
            throw new IllegalArgumentException("This day does not exist!");
        }
        day = d;
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

    public void setDay(int day ) {
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

    public  void setDate(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    private boolean isYearValid(int year) {
        return (year <= MIN_YEAR) || (year >= MAX_YEAR);
    }

    private boolean isMonthValid(int month) {
        return (month < 1) || (month > 12);
    }

    private boolean isDayValid(int day, int month, int year)
    {
        return ((day < 1) || ((month != 1 || day > 31) &&
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
        switch (dayOfWeek) {
            case(1):
                return (Weekday.MONDAY);
            case(2):
                return (Weekday.TUESDAY);
            case(3):
                return (Weekday.WEDNESDAY);
            case(4):
                return (Weekday.THURSDAY);
            case(5):
                return (Weekday.FRIDAY);
            case(6):
                return (Weekday.SATURDAY);
            default:
                return (Weekday.SUNDAY);
        }
    }
}
