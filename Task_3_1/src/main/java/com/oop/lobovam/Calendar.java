package com.oop.lobovam;
import static com.oop.lobovam.Date.isLeapYear;

public class Calendar {

    public  int daysInMonths(int month, int year) {
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

    public Date addTime(long number, Date from, String arg) {
        Date date = new Date();
        int day = from.getDay();
        int month = from.getMonth();
        int year = from.getYear();

        if(arg == "y") {
            year +=(int)number;
        } else if(arg == "m") {
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
        } else if(arg =="d") {
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
            while(number!=0) {
                if(number > numberOfDays) {
                    month++;
                    number-=numberOfDays;
                } else {
                    day = (int) number;
                    break;
                }
                if(month>12) {
                    year++;
                    month-=12;
                }
                numberOfDays = daysInMonths(month, year);
            }
        } else
            throw new IllegalArgumentException("Call function with valid arguments - d, m or y to add days, months or years");
        date.setDate(day, month, year);
        return date;
    }


    public Date subTime(long number, Date from, String arg) {
        Date date = new Date();
        int day, month, year;
        day = from.getDay();
        month = from.getMonth();
        year = from.getYear();

        if(arg == "y") {
            year -=(int)number;
        } else if(arg == "m") {
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
        } else if(arg =="d") {
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
                    day = numberOfDays - (int) number;
                    break;
                }
                if(month<1) {
                    year--;
                    month+=12;
                }
                numberOfDays = daysInMonths(month, year);
            }
        } else
            throw new IllegalArgumentException("Call function with valid arguments - d, m or y to add days, months or years");
        date.setDate(day, month, year);
        return date;
    }

    public void subDates(Date minuend, Date subtrahend) {
        int year=0;
        int month=0;
        int day=0;
        while(subtrahend.getYear()!= minuend.getYear()) {
            subtrahend = addTime(1, subtrahend, "y");
            year++;
        }
        if(subtrahend.getMonth() > minuend.getMonth()) {
            while (subtrahend.getMonth()!=minuend.getMonth()){
                subtrahend = subTime(1, subtrahend, "m");
                month--;
            }
        } else {
            while (subtrahend.getMonth() != minuend.getMonth()) {
                subtrahend = addTime(1, subtrahend, "m");
                month++;
            }
        }
        if(month<0){
            year--;
            month = 12 + month;
        }
        if(subtrahend.getDay() > minuend.getDay()) {
            while (subtrahend.getDay()!=minuend.getDay()){
                subtrahend = subTime(1, subtrahend, "d");
                day--;
            }
        } else {
            while (subtrahend.getDay() != minuend.getDay()) {
                subtrahend = addTime(1, subtrahend, "d");
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
    }

}



