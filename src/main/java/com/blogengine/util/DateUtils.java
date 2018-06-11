package com.blogengine.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fernando
 */
public final class DateUtils {

    private static final String PATTERN = "dd/MM/yyyy";

    public static Date newDate(String date) {
        return newDate(date, PATTERN);
    }

    public static Date newDate(String date, String pattern) {
        try {
            DateFormat formatter = new SimpleDateFormat(pattern);
            return new Date(formatter.parse(date).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
