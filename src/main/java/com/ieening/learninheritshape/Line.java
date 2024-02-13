package com.ieening.learninheritshape;

public class Line extends Shape {
    private Point start;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    private Point end;

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Line(Point start, Point end, String color) {
        super(color);
        this.start = start;
        this.end = end;
    }

    public double length() {
        return start.distance(end);
    }

    @Override
    public void draw() {
        System.out.println(
                "draw line from " + start.toString() + " to " + end.toString() + ", using color " + super.getColor());
    }

    public static void main(String[] args) {
        Line line = new Line(new Point(0, 0), new Point(1, 1), "red");
        System.out.println(line.length());
        line.draw();
    }
}
