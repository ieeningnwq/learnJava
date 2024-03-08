package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

public class TestTreeSet {
    @Test
    public void testTreeSetComparable() {
        TreeSet<String> words = new TreeSet<String>();
        words.addAll(Arrays.asList(new String[] {
                "tree", "map", "hash", "map",
        }));
        StringBuilder sBuilder = new StringBuilder();
        for (String w : words) {
            sBuilder.append(w + " ");
        }
        assertTrue("hash map tree ".equals(sBuilder.toString()));
    }

    @Test
    public void testTreeSetComparator() {
        TreeSet<String> words = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        words.addAll(Arrays.asList(new String[] {
                "tree", "Map", "Hash", "map",
        }));
        StringBuilder sBuilder = new StringBuilder();
        for (String w : words) {
            sBuilder.append(w + " ");
        }
        assertTrue("Hash Map tree ".equals(sBuilder.toString()));
    }
}
