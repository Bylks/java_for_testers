package ru.stqa.geometry.figures;

public class Triangle {
double a,b,c;
    public void printTriangleArea() {
        double area = calculateTriangeArea();
        var text = String.format ("Площадь треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c,area);
        System.out.println(text);
    }
    public double calculateTriangeArea()
    {
        double p = (a+b+c)/2;
        double area = Math.sqrt(p*(p-a)*(p-b)*(p-c)); //Считаем площадь
        return area;
    }
   public Triangle (double a1,double b1, double c1){
        a=a1;//Задаём поля в конструкторе
        b=b1;
        c=c1;

    }
    public double calculateTriangePerimeter() {
        return (a+b+c);//Считаем периметр
    }

    public void printTrianglePerimeter() {
        System.out.println(String.format("Периметр треугольника со сторонами %.2f , %.2f, %.2f равна %.2f",a,b,c,calculateTriangePerimeter()));
    }
}
