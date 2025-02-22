package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        // Square.printSquareArea(7.0);
        //  Rectangle.printRectangleArea (7.0,4.0);
           Triangle tr1 = new Triangle(3.0,4.0,5.0);
         tr1.printTriangleArea();
        tr1.printTrianglePerimeter();
        //  System.out.println("123123");
        //  Triangle.printTriangleArea (3.0,4.0,5.0);
    }

}
