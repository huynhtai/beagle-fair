package ch.smartlinksa.intern.interfaces.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static boolean isFormatDate(String dateInputString) {

        simpleDateFormat.setLenient(false);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(dateInputString);
            System.out.println("Date after validation: " + date);
        }
        catch (ParseException e)
        {
            System.out.println("The date you provided is in an " +"invalid date format.");
            return false;
        }
        return true;
    }

    public static boolean isFutureDate(String dateInputString){
        simpleDateFormat.setLenient(false);
        Date dateInput = null;
        Date currentDate = new Date();
        try
        {
            currentDate = simpleDateFormat.parse(currentDate.toString());
            dateInput = simpleDateFormat.parse(dateInputString);

        }
        catch (ParseException e)
        {
            return false;
        }
        return compareToDate(currentDate, dateInput);
    }

    private static boolean compareToDate (Date currentDate, Date inputDate){
        if(inputDate.compareTo(currentDate) == 1){
            return false;
        }

        return true;
    }

}