package ru.stqa.geometry.figures;
public class Triangle {

    public static void printTriangleArea(double a, double b, double c) {
        double p= (a+b+c)/2; // sqrt(p*(p-a)*(p-b)*(p-c))
        double area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println(String.format ("Площадь треугольника со сторонами %f , %f, %f равна %f",a,b,c,area));

    }
}
