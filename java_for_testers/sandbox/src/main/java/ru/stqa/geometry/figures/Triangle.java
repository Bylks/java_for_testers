package ru.stqa.geometry.figures;

import java.util.Arrays;
import java.util.Objects;

public record Triangle (double a, double b, double c) {
    public Triangle
    {
if (a<=0||b<=0||c<=0)
{
    throw new IllegalArgumentException("Some sides of created triangle are less or equal to zero");
} else if ((a>b+c)||(b>a+c)||(c>b+a)) {
    throw new IllegalArgumentException("Created triangle is non-existent");
}
    }

    public void printTriangleArea() {
        double area = calculateTriangleArea();
        var text = String.format ("Площадь треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c,area);
        System.out.println(text);
    }
    public double calculateTriangleArea()
    {
        double p = (a+b+c)/2;
        //Считаем площадь
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double calculateTrianglePerimeter() {
        return (a+b+c);//Считаем периметр
    }

    public void printTrianglePerimeter() {
        System.out.println(String.format("Периметр треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c, calculateTrianglePerimeter()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Arrays.equals(this.calculateSortedSides(), triangle.calculateSortedSides()));
/*             ((Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)||
                (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.b) == 0)||
                (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.a) == 0)||
                (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.c) == 0)||
                (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0)||
                (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.a) == 0)||);//многобуков*/
    }
//Возвращает массив отсортированных сторон
    private double[] calculateSortedSides() {
        double[] sortedsides = {a,b,c};
        Arrays.sort(sortedsides);
        return sortedsides;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
