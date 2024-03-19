package com.ieening.learnSerializable.shape;

public class CircleWithTypeInfo extends ShapeWithTypeInfo {
    int r;

    public CircleWithTypeInfo() {
    }

    public CircleWithTypeInfo(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

}
