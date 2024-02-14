package com.ieening.learnInterface;

import java.util.Objects;

public class CompareUtil {
    public static Object max(MyComaprable[] objs) {
        if (Objects.isNull(objs) || objs.length == 0) {
            return null;
        }
        MyComaprable max = objs[0];
        for (int i = 1; i < objs.length; i++) {
            if (max.compareTo(objs[i]) < 0) {
                max = objs[i];
            }
        }
        return max;
    }

    public static void sort(MyComaprable[] objs) {
        if (!(Objects.isNull(objs) || objs.length == 0)) {
            for (int i = 0; i < objs.length; i++) {
                int min = i;
                for (int j = i + 1; j < objs.length; j++) {
                    if (objs[j].compareTo(objs[min]) < 0) {
                        min = j;
                    }
                }
                if (min != i) {
                    MyComaprable temp = objs[i];
                    objs[i] = objs[min];
                    objs[min] = temp;
                }
            }
        }
    }
}
