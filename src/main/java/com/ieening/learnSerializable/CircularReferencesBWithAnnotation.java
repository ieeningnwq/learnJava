package com.ieening.learnSerializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class CircularReferencesBWithAnnotation {
    String name;
    @JsonBackReference
    CircularReferencesAWithAnnotation a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferencesAWithAnnotation getA() {
        return a;
    }

    public void setA(CircularReferencesAWithAnnotation a) {
        this.a = a;
    }

}
