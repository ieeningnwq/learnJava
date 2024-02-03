package com.ieening;

import java.util.Scanner;

public class QuadraticEquationWithOneUnknownTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入参数 a 的值:");
        int a = input.nextInt();
        System.out.print("请输入参数 b 的值:");
        int b = input.nextInt();
        System.out.print("请输入参数 c 的值:");
        int c = input.nextInt();
        input.close();
        if (a != 0) {
            double d = b * b - 4 * a * c;
            if (d > 0) {
                double x1 = (-b + Math.sqrt(d)) / (2 * a);
                double x2 = (-b - Math.sqrt(d)) / (2 * a);
                System.out.println("一元二次方程 " + a + "*x^2+" + b + "x+" + c + "=0" + "有两个实数解: x1=" + x1 + ", x2=" + x2);
            } else if (d == 0) {
                int x = -b / (2 * a);
                System.out.println("一元二次方程 " + a + "*x^2+" + b + "x+" + c + "=0" + "有两个相同的实数解: x1=x2=" + x);
            } else {
                // d<0
                System.out.println("一元二次方程在实数范围内无解");
            }
        } else { // 即: a==0
            if (b != 0) {
                double x = -c / b;
                System.out.println("一元一次方程的解: x=" + x);
            } else {
                System.out.println("输入有误，a=" + a + ",b=" + b + ",c=" + c + " 不能构成一个方程");
            }
        }
    }
}
