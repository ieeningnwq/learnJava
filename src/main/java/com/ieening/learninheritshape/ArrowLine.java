package com.ieening.learninheritshape;

public class ArrowLine extends Line {
    private boolean startArrow;

    public boolean isStartArrow() {
        return startArrow;
    }

    public void setStartArrow(boolean startArrow) {
        this.startArrow = startArrow;
    }

    private boolean endArrow;

    public boolean isEndArrow() {
        return endArrow;
    }

    public void setEndArrow(boolean endArrow) {
        this.endArrow = endArrow;
    }

    public ArrowLine(Point start, Point end, String color, boolean startArrow, boolean endArrow) {
        super(start, end, color);
        this.startArrow = startArrow;
        this.endArrow = endArrow;
    }

    @Override
    public void draw() {
        if (startArrow) {
            System.out.print("draw start arrow, ");
        }
        if (endArrow) {
            System.out.print("draw end arrow");
        }
        super.draw();
    }

    public static void main(String[] args) {
        ArrowLine arrowLine = new ArrowLine(new Point(0, 0), new Point(2, 2), "blue", true, true);
        arrowLine.draw();
    }
}
