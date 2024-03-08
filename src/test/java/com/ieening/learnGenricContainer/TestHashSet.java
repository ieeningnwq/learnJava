package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;

public class TestHashSet {
    @Test
    public void testContractor() {
        HashSet<String> set = new HashSet<String>();
        set.add("hello");
        set.add("world");
        set.addAll(Arrays.asList(new String[] { "hello", "你好" }));
        String[] stringArray = new String[] { "hello", "world", "你好" };
        for (String s : stringArray) {
            set.remove(s);
        }
        assertTrue(set.isEmpty());
    }
}
