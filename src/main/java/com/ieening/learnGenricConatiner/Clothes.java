package com.ieening.learnGenricConatiner;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Clothes {
    private String id;
    private Size size;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Map<Size, Integer> countBySize(List<Clothes> clothes) {
        Map<Size, Integer> map = new EnumMap<>(Size.class);
        for (Clothes c : clothes) {
            Size size = c.getSize();
            Integer count = map.get(size);
            if (count != null) {
                map.put(size, count + 1);
            } else {
                map.put(size, 1);
            }
        }
        return map;
    }

    public Clothes(String id, Size size) {
        this.id = id;
        this.size = size;
    }

    public static int[] countBySizeArray(List<Clothes> clothes) {
        int[] stat = new int[Size.values().length];
        for (Clothes c : clothes) {
            Size size = c.getSize();
            stat[size.ordinal()]++;
        }
        return stat;
    }

}
