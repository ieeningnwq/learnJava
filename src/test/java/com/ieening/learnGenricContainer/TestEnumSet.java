package com.ieening.learnGenricContainer;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.ieening.learnGenricConatiner.Day;
import com.ieening.learnGenricConatiner.Worker;

public class TestEnumSet {
    @Test
    public void testEnumSetWeekend() {
        Set<Day> weekend = EnumSet.noneOf(Day.class);
        weekend.add(Day.SATURDAY);
        weekend.add(Day.SUNDAY);
        assertEquals("[SATURDAY, SUNDAY]", weekend.toString());
    }

    @Test
    public void testEnumSetWorkersAllNotWork() {
        Worker[] workers = new Worker[] {
                new Worker("张三", EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        Set<Day> days = EnumSet.allOf(Day.class);
        for (Worker w : workers) {
            days.removeAll(w.getAvailableDays());
        }
        assertEquals("[SUNDAY]", days.toString());
    }

    @Test
    public void testEnumSetWorkersAtLeastOneWork() {
        Worker[] workers = new Worker[] {
                new Worker("张三", EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        Set<Day> days = EnumSet.noneOf(Day.class);
        for (Worker w : workers) {
            days.addAll(w.getAvailableDays());
        }
        assertEquals("[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY]", days.toString());
    }

    @Test
    public void testEnumSetWorkersAllWork() {
        Worker[] workers = new Worker[] {
                new Worker("张三", EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        Set<Day> days = EnumSet.allOf(Day.class);
        for (Worker w : workers) {
            days.retainAll(w.getAvailableDays());
        }
        assertEquals("[TUESDAY]", days.toString());
    }

    @Test
    public void testEnumSetWorkersMonThusWork() {
        Worker[] workers = new Worker[] {
                new Worker("张三", EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        Set<Worker> availableWorkers = new HashSet<Worker>();
        for (Worker w : workers) {
            if (w.getAvailableDays().containsAll(
                    EnumSet.of(Day.MONDAY, Day.TUESDAY))) {
                availableWorkers.add(w);
            }
        }
        assertEquals("[Worker [name=张三, availableDays=[MONDAY, TUESDAY, WEDNESDAY, FRIDAY]]]",
                availableWorkers.toString());
    }

    @Test
    public void testEnumSetWorkersMoreTwoWorkersDay() {
        Worker[] workers = new Worker[] {
                new Worker("张三", EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        Map<Day, Integer> countMap = new EnumMap<>(Day.class);
        for (Worker w : workers) {
            for (Day d : w.getAvailableDays()) {
                Integer count = countMap.get(d);
                countMap.put(d, count == null ? 1 : count + 1);
            }
        }
        Set<Day> days = EnumSet.noneOf(Day.class);
        for (Map.Entry<Day, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= 2) {
                days.add(entry.getKey());
            }
        }
        assertEquals("[TUESDAY, THURSDAY]", days.toString());
    }
}
