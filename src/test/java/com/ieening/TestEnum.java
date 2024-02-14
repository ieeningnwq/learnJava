package com.ieening;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ieening.learnEnum.Season;
import com.ieening.learnEnum.SeasonClass;

public class TestEnum {
    @Test
    public void testSeasonClass() {
        SeasonClass spring = SeasonClass.SPRING;
        assertEquals(SeasonClass.SPRING, spring);
    }

    @Test
    public void testEnumToString() {
        assertEquals("SPRING", Season.SPRING.toString());
    }

    @Test
    public void testEnumName() {
        assertEquals("SPRING", Season.SPRING.name());
    }

    @Test
    public void testEnumEquals() {
        Season summer = Season.SUMMER;
        assertTrue(summer == Season.SUMMER);
        assertTrue(summer.equals(Season.SUMMER));
        assertFalse(summer == Season.AUTOM);
    }

    @Test
    public void testEnumOrdinal() {
        assertTrue(0 == Season.SPRING.ordinal());
        assertTrue(1 == Season.SUMMER.ordinal());
        assertTrue(2 == Season.AUTOM.ordinal());
        assertTrue(3 == Season.WINTER.ordinal());
    }

    @Test
    public void testEnumComparable() {
        Season summer = Season.SUMMER;
        assertEquals(1, summer.compareTo(Season.SPRING));
        assertEquals(-1, summer.compareTo(Season.AUTOM));
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(Season.SPRING, Season.valueOf("SPRING"));
    }

    @Test
    public void testEnumValues() {
        assertArrayEquals(new Season[] { Season.SPRING, Season.SUMMER, Season.AUTOM, Season.WINTER }, Season.values());
    }
}
