package com.ieening.learnGenricConatiner;

import java.util.Comparator;

public class Task {
    int priority;
    String name;

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public static Comparator<Task> taskComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1.getPriority() > o2.getPriority()) {
                return -1;
            } else if (o1.getPriority() < o2.getPriority()) {
                return 1;
            }
            return 0;
        }
    };

    @Override
    public String toString() {
        return "Task [priority=" + priority + ", name=" + name + "]";
    }

}
