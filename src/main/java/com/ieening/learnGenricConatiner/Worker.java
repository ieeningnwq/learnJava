package com.ieening.learnGenricConatiner;

import java.util.Set;

public class Worker {
    private String name;
    private Set<Day> availableDays;

    public Worker(String name, Set<Day> availableDays) {
        this.name = name;
        this.availableDays = availableDays;
    }

    public String getName() {
        return name;
    }

    public Set<Day> getAvailableDays() {
        return availableDays;
    }

    @Override
    public String toString() {
        return "Worker [name=" + name + ", availableDays=" + availableDays + "]";
    }
}
