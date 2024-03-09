package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TestLinkedHashMap {
    @Test
    public void testLinkedHashMapAccessOrderFalse() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("c", 100);
        linkedHashMap.put("d", 200);
        linkedHashMap.put("e", 500);
        linkedHashMap.put("u", 600);
        linkedHashMap.put("d", 300);
        StringBuilder sBuilder = new StringBuilder();
        for (Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            sBuilder.append(entry.getKey() + " " + entry.getValue() + "\t");
        }
        assertTrue("c 100\td 300\ta 500\t".equals(sBuilder.toString()));
    }

    @Test
    public void testLinkedHashMapAccessOrderTrue() {
        Map<String, Integer> accessMap = new LinkedHashMap<>(16, 0.75f, true);
        accessMap.put("c", 100);
        accessMap.put("d", 200);
        accessMap.put("a", 500);
        accessMap.get("c");
        accessMap.put("d", 300);
        StringBuilder sBuilder = new StringBuilder();
        for (Entry<String, Integer> entry : accessMap.entrySet()) {
            sBuilder.append(entry.getKey() + " " + entry.getValue() + "\t");
        }
        assertTrue("a 500\tc 100\td 300\t".equals(sBuilder.toString()));
    }

}
