package ch.smartlinksa.intern.interfaces.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormat {

    final static String DATE_FORMAT = "dd-MM-yyyy";
    public static boolean isFormatDate(String inputDateString) {
        try {
            java.text.DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(inputDateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}