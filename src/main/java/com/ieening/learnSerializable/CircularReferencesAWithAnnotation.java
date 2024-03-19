package com.ieening.learnSerializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CircularReferencesAWithAnnotation {

    String name;
    @JsonManagedReference
    CircularReferencesBWithAnnotation b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferencesBWithAnnotation getB() {
        return b;
    }

    public void setB(CircularReferencesBWithAnnotation b) {
        this.b = b;
    }
}
