package com.ieening.learnAnnotation.simpleFormatter;

import java.lang.reflect.Field;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class SimpleFormatter {
    public static String format(Object object) {
        Class<?> objectClass = object.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : objectClass.getDeclaredFields()) {
            if (!field.canAccess(object)) {
                field.setAccessible(true);
            }
            Label label = field.getAnnotation(Label.class);
            String name = label != null ? label.value() : field.getName();
            Object value;
            try {
                value = field.get(object);
                if (value != null && field.getType() == Date.class) {
                    value = formatDate(field, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                value = "NA";
            }
            stringBuilder.append(name + ":" + value + "\n");
        }
        return stringBuilder.toString();
    }

    private static Object formatDate(Field field, Object value) {
        Format format = field.getAnnotation(Format.class);
        if (format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
            sdf.setTimeZone(TimeZone.getTimeZone(format.timezone()));
            return sdf.format(value);
        }
        return value;
    }
}
