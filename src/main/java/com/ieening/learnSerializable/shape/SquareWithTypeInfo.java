package com.ieening.learnSerializable.shape;

public class SquareWithTypeInfo extends ShapeWithTypeInfo{
    int l;

    public SquareWithTypeInfo() {
    }

    public SquareWithTypeInfo(int l) {
        this.l = l;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }
}
