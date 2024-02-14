package com.ieening;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ieening.learnInterface.CompareUtil;
import com.ieening.learnInterface.MyComaprable;
import com.ieening.learnInterface.Point;

public class TestCompareUtil {
    @Test
    public void testMax() {
        MyComaprable[] points = new MyComaprable[] { (MyComaprable) new Point(1, 2), (MyComaprable) new Point(2, 1),
                (MyComaprable) new Point(1, 1), (MyComaprable) new Point(-1, -3) };
        Point point = (Point) CompareUtil.max(points);
        assertTrue(point.compareTo(new Point(-1, -3)) == 0);
    }

    @Test
    public void testSort() {
        MyComaprable[] points = new MyComaprable[] { (MyComaprable) new Point(1, 2), (MyComaprable) new Point(2, 1),
                (MyComaprable) new Point(1, 1), (MyComaprable) new Point(-1, -3) };
        CompareUtil.sort(points);
        for (MyComaprable point : points) {
            System.out.println((Point) point);
        }
    }
}
