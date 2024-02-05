package com.ieening;

public class ClassTest {
    public static void main(String[] args) {
        Point point;
        point = new Point(3, 4);
        System.out.println("Point:x=" + point.getX() + ",y=" + point.getY());
        System.out.println(point.distance());
    }
}

/**
 * Point
 */
class Point {

    private int x;
    private int y;

    Point() {
        this(0, 0);
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

}
