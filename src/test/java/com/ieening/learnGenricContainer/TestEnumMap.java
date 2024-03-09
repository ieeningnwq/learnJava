package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ieening.learnGenricConatiner.Clothes;
import com.ieening.learnGenricConatiner.Size;

public class TestEnumMap {
    @Test
    public void testClothesCountBySize() {
        List<Clothes> clothes = Arrays.asList(new Clothes[] {
                new Clothes("C001", Size.SMALL), new Clothes("C002", Size.LARGE),
                new Clothes("C003", Size.LARGE), new Clothes("C004", Size.MEDIUM),
                new Clothes("C005", Size.SMALL), new Clothes("C006", Size.SMALL),
        });
        assertEquals("{SMALL=3, MEDIUM=1, LARGE=2}", Clothes.countBySize(clothes).toString());
    }

    @Test
    public void testClothesCountBySizeArray() {
        List<Clothes> clothes = Arrays.asList(new Clothes[] {
                new Clothes("C001", Size.SMALL), new Clothes("C002", Size.LARGE),
                new Clothes("C003", Size.LARGE), new Clothes("C004", Size.MEDIUM),
                new Clothes("C005", Size.SMALL), new Clothes("C006", Size.SMALL),
        });
        int[] stat = Clothes.countBySizeArray(clothes);
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < stat.length; i++) {
            sBuilder.append(Size.values()[i] + ": " + stat[i] + "\t");
        }
        assertEquals("SMALL: 3\tMEDIUM: 1\tLARGE: 2\t", sBuilder.toString());
    }
}
