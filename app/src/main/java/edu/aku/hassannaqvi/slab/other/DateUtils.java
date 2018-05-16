package edu.aku.hassannaqvi.slab.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ramsha.ahmed on 4/14/2018.
 */

public class DateUtils {
public static String getThreeDaysBack(String format, int days){
    Calendar cal = Calendar.getInstance();
    cal.setTime(cal.getTime());
    cal.add(Calendar.DATE, days);
    return new SimpleDateFormat(format).format(cal.getTime()).toString(); //"dd-MM-yyyy HH:mm"
}

}
