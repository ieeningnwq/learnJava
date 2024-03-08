package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;

import org.junit.Test;

import java.util.TreeMap;
import java.util.TreeSet;

public class TestTreeMap {
    @Test
    public void testTreeMapSortComparable() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("a", "abstract");
        map.put("c", "call");
        map.put("b", "basic");
        map.put("T", "tree");
        StringBuilder sBuilder = new StringBuilder();
        for (Entry<String, String> kv : map.entrySet()) {
            sBuilder.append(kv.getKey() + "=" + kv.getValue() + " ");
        }
        assertTrue("T=tree a=abstract b=basic c=call ".equals(sBuilder.toString()));
    }

    @Test
    public void testTreeMapComaptor() {
        TreeMap<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        map.put("a", "abstract");
        map.put("c", "call");
        map.put("b", "basic");
        map.put("T", "tree");
        StringBuilder sBuilder = new StringBuilder();
        for (Entry<String, String> kv : map.entrySet()) {
            sBuilder.append(kv.getKey() + "=" + kv.getValue() + " ");
        }
        assertTrue("a=abstract b=basic c=call T=tree ".equals(sBuilder.toString()));
    }

    @Test
    public void testTreeMapDeduplication() {
        TreeMap<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        map.put("t", "lowert");
        map.put("T", "UpperT");

        StringBuilder sBuilder = new StringBuilder();
        for (Entry<String, String> kv : map.entrySet()) {
            sBuilder.append(kv.getKey() + "=" + kv.getValue() + " ");
        }
        assertTrue("t=UpperT ".equals(sBuilder.toString()));
    }
}
