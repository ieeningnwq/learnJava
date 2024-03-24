package com.ieening.learnConcurrency.concurrentUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateFormat {
    static ThreadLocal<DateFormat> sdf = new ThreadLocal<>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String dateString(Date date) {
        return sdf.get().format(date);
    }

    public static Date string2Date(String formatString) throws ParseException {
        return sdf.get().parse(formatString);
    }
}
