package com.ieening.learnSerializable;

public class CircularReferencesB {
    String name;
    CircularReferencesA a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferencesA getA() {
        return a;
    }

    public void setA(CircularReferencesA a) {
        this.a = a;
    }

}
