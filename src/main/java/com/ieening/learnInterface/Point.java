package com.ieening.learnInterface;

public class Point implements MyComaprable {
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int y;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public int compareTo(Object other) {
        if (other instanceof Point) {
            Point otherPoint = (Point) other;
            double delta = distance() - otherPoint.distance();
            if (delta < 0) {
                return -1;

            } else if (delta > 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            throw new IllegalArgumentException(other.toString() + " is not instanceof " + this.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}
