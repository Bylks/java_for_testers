package ru.stqa.geometry.figures;

public record Triangle (double a, double b,double c) {
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
}
