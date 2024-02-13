package com.ieening.learninheritshape;

public class Circle extends Shape {
    // 中心点
    private Point center;
    // 半径
    private double r;

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
    }

    @Override
    public void draw() {
        System.out.println("draw circle at " + center.toString() + " with r " + r
                + ", using color : " + getColor());
    }

    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public String toString() {
        return "Circle [center=" + center + ", r=" + r + "]";
    }

    public static void main(String[] args) {
        Point center = new Point(3, 4);
        // 创建圆，赋值给circle
        Circle circle = new Circle(center, 2);
        // 调用draw方法，会执行Circle的draw方法
        circle.draw();
        // 输出圆面积
        System.out.println(circle.area());
    }
}
