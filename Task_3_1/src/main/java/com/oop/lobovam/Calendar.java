package com.oop.lobovam;
import static com.oop.lobovam.Date.isLeapYear;

public class Calendar {

    private int daysInMonths(int month, int year) {
        switch (month) {
            case(1):
            case(3):
            case(5):
            case(7):
            case(8):
            case(10):
            case(12):
                return 31;
            case(2):
                if(isLeapYear(year))
                    return 29;
                else
                    return 28;
            default:
                return 30;
        }
    }
    public Date addDays(int number, Date from) {
        int day = from.getDay();
        int month = from.getMonth();
        int year = from.getYear();
        int numberOfDays = daysInMonths(month, year);
        if(number <= numberOfDays-day) {
            day+=number;
            number = 0;
        } else {
            number -= numberOfDays - day+1;
            day = 1;
            month++;
            if(month>12) {
                year++;
                month-=12;
            }
        }
        numberOfDays = daysInMonths(month, year);
        while(number!=0) {
            if (number > numberOfDays) {
                month++;
                number -= numberOfDays;
            } else {
                day += number;
                break;
            }
            if (month > 12) {
                year++;
                month -= 12;
            }
            numberOfDays = daysInMonths(month, year);
        }
        return new Date(day, month, year);
    }

    public Date addMonths(int number, Date from) {
        int day = from.getDay();
        int month = from.getMonth();
        int year = from.getYear();
        month += number;
        if(month > 12) {
            year = year + month / 12;
            month = month%12;
            int numberOfDays = daysInMonths(month, year);
            if(day > numberOfDays) {
                day = day - numberOfDays;
                month++;
            }
        }
        return new Date(day, month, year);
    }

    public Date addYears(int number, Date from) {
        return new Date(from.getDay(), from.getMonth(), from.getYear()+number);
    }



    public Date subDays(int number, Date from) {
        int day = from.getDay();
        int month = from.getMonth();
        int year = from.getYear();
        int numberOfDays;
        if(number < day) {
            day-=number;
            number = 0;
        } else {
            number -= day;
            month--;
            if(month<1) {
                year--;
                month+=12;
            }
            numberOfDays = daysInMonths(month, year);
            day = numberOfDays;

        }
        numberOfDays = daysInMonths(month, year);
        while(number!=0) {
            if(number > numberOfDays) {
                month--;
                number-=numberOfDays;
            } else {
                day = numberOfDays - number;
                break;
            }
            if(month<1) {
                year--;
                month+=12;
            }
            numberOfDays = daysInMonths(month, year);
        }
        return new Date(day, month, year);
    }

    public Date subMonths(int number, Date from) {
        int day = from.getDay();
        int month = from.getMonth();
        int year = from.getYear();
        if(month-number<1){
            month += number;
            year -= month/12;
            month = month%12;
        } else {
            month -= number;
        }
        int numberOfDays = daysInMonths(month, year);
        if(numberOfDays<day) {
            day-=numberOfDays;
            month++;
        }
        return new Date(day, month, year);
    }

    public Date subYears(int number, Date from) {
        return new Date(from.getDay(), from.getMonth(), from.getYear() - number);
    }

    public Date subDates(Date minuend, Date subtrahend) {
        int year=0;
        int month=0;
        int day=0;
        while(subtrahend.getYear()!= minuend.getYear()) {
            subtrahend = addYears(1, subtrahend);
            year++;
        }
        if(subtrahend.getMonth() > minuend.getMonth()) {
            while (subtrahend.getMonth()!=minuend.getMonth()){
                subtrahend = subMonths(1, subtrahend);
                month--;
            }
        } else {
            while (subtrahend.getMonth() != minuend.getMonth()) {
                subtrahend = addMonths(1, subtrahend);
                month++;
            }
        }
        if(month<0){
            year--;
            month = 12 + month;
        }
        if(subtrahend.getDay() > minuend.getDay()) {
            while (subtrahend.getDay()!=minuend.getDay()){
                subtrahend = subDays(1, subtrahend);
                day--;
            }
        } else {
            while (subtrahend.getDay() != minuend.getDay()) {
                subtrahend = addDays(1, subtrahend);
                day++;
            }
        }
        if(day<0 && -day>daysInMonths(month, year)) {
            day = daysInMonths(month, year) - day;
            month--;
            if(month<0){
                year--;
                month = 12 - month;
            }
        } else if(day<0)
            day = -day;
        System.out.println(day + " days," + month + " month," + year + "years.");
        return new Date(day, month, year);
    }

}



