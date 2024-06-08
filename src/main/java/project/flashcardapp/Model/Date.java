package project.flashcardapp.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;


public class Date {
    static LocalDate now = LocalDate.now();
    static DayOfWeek dayOfWeek = now.getDayOfWeek();
    static int curentMonth = now.getMonthValue();

    public static String getDayofWeek() {
        switch (dayOfWeek) {
            case SUNDAY: return "Sunday";
            case MONDAY: return "Monday";
            case TUESDAY: return "Tuesday";
            case WEDNESDAY: return "Wednesday";
            case THURSDAY: return "Thursday";
            case FRIDAY: return "Friday";
            case SATURDAY: return "Saturday";
            default: return "";
        }
    }

    public static String getMonth() {
        switch (curentMonth) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }
}
