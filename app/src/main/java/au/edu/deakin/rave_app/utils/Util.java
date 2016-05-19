package au.edu.deakin.rave_app.utils;

import com.google.gson.JsonPrimitive;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Util {

    private final static String DATE_TIME_NO_SUB_SEC_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static Date stringToDate(String dateString)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_NO_SUB_SEC_PATTERN);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date src)
    {
        if (src == null) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_NO_SUB_SEC_PATTERN);
        return dateFormat.format(src);

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String dateToString(Date date, String format)
    {
        if (date == null) return "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        // OVERRIDE SOME symbols WHILE RETAINING OTHERS
        symbols.setAmPmStrings(new String[] { "am", "pm" });
        dateFormat.setDateFormatSymbols(symbols);

        String dateString = dateFormat.format(date);
        if(!format.equals("h:mm a"))
        {
            dateString=dateString.substring(0,2)+dateString.substring(2,6).toUpperCase()+dateString.substring(6,dateString.length());
        }

        return dateString;
    }
}
