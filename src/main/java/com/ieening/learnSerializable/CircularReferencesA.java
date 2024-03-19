package com.ieening.learnSerializable;

public class CircularReferencesA {

    String name;
    CircularReferencesB b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferencesB getB() {
        return b;
    }

    public void setB(CircularReferencesB b) {
        this.b = b;
    }

}
