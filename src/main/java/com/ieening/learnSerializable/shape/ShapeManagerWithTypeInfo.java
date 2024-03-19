package com.ieening.learnSerializable.shape;

import java.util.List;

public class ShapeManagerWithTypeInfo {
    List<ShapeWithTypeInfo> shapes;

    public List<ShapeWithTypeInfo> getShapes() {
        return shapes;
    }

    public void setShapes(List<ShapeWithTypeInfo> shapes) {
        this.shapes = shapes;
    }
}
