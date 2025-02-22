package ru.stqa.geometry.figures;

public record Triangle (double a, double b,double c) {
    public void printTriangleArea() {
        double area = calculateTriangeArea();
        var text = String.format ("Площадь треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c,area);
        System.out.println(text);
    }
    public double calculateTriangeArea()
    {
        double p = (a+b+c)/2;
        //Считаем площадь
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double calculateTriangePerimeter() {
        return (a+b+c);//Считаем периметр
    }

    public void printTrianglePerimeter() {
        System.out.println(String.format("Периметр треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c,calculateTriangePerimeter()));
    }
}
