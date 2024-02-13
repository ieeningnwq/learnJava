package com.ieening;

public class ClassTest extends Object {
    public static void main(String[] args) {
        Point point;
        point = new Point(3, 4);
        System.out.println("Point:x=" + point.getX() + ",y=" + point.getY());
        System.out.println(point.distance());
        System.out.println(point.toString());
    }
}
