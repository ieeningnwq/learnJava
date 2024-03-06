package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class TestHashMap {
    @Test
    public void testHashMapView() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "nwq");
        hashMap.put("age", "18");
        hashMap.keySet().clear();
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testHashMapBasics() {
        Random rnd = new Random(150);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int num = rnd.nextInt(4);
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, count + 1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Integer, Integer> kv : countMap.entrySet()) {
            stringBuilder.append(kv.getKey() + ", " + kv.getValue() + ", ");
        }
        assertTrue("0, 253, 1, 230, 2, 243, 3, 274, ".equals(stringBuilder.toString()));
    }

    

}
