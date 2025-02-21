package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.0);
        Rectangle.printRectangleArea (7.0,4.0);
        Triangle.printTriangleArea (3.0,4.0,5.0);
    }

}
