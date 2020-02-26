package edu.aku.hassannaqvi.slab.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ramsha.ahmed on 4/14/2018.
 */

public class DateUtils {
    public static String getThreeDaysBack(String format, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.DATE, days);
        return new SimpleDateFormat(format).format(cal.getTime()); //"dd-MM-yyyy HH:mm"
    }

    public static Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long ageInMonthsByDOB(String dateStr) {
        Calendar cal = getCalendarDate(dateStr);
        Date dob = cal.getTime();
        Date today = new Date();
        Long diff = today.getTime() - dob.getTime();
        long ageInMonths = (diff / (24 * 60 * 60 * 1000)) / 30;
        return ageInMonths;
    }

    public static long ageInDaysByDOB(String dateStr) {
        Calendar cal = getCalendarDate(dateStr);
        Date dob = cal.getTime();
        Date today = new Date();
        Long diff = today.getTime() - dob.getTime();
        long ageInDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return ageInDays + 1;
    }

}
