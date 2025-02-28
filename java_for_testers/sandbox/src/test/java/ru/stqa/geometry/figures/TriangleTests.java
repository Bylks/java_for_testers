package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    Triangle trtest1 = new Triangle(3.0, 4.0, 5.0);
    Triangle trtest2 = new Triangle(6.0, 8.0, 10.0);
    Triangle trtest3 = new Triangle(10.0, 12.0, 15.0);

    @Test
    void canCalculateTriangleAreaTest1() {
        Assertions.assertEquals(6, trtest1.calculateTriangleArea());
    }

    @Test
    void canCalculateTriangleAreaTest2() {
        Assertions.assertEquals(24, trtest2.calculateTriangleArea());
    }

    @Test
    void canCalculateTriangleAreaTest3() {
        Assertions.assertEquals(59.81168364124187, trtest3.calculateTriangleArea());
    }

    @Test
    void canCalculateTrianglePerimeterTest1() {
        Assertions.assertEquals(12, trtest1.calculateTrianglePerimeter());
    }

    @Test
    void canCalculateTrianglePerimeterTest2() {
        Assertions.assertEquals(24, trtest2.calculateTrianglePerimeter());
    }

    @Test
    void canCalculateTrianglePerimeterTest3() {
        Assertions.assertEquals(24, trtest3.calculateTrianglePerimeter());
    }

    @Test
    void canCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {//OK
        }

    }

    @Test
    void canCreateNonExistTriangle() {
        try {
            new Triangle(1.0, 2.0, 13.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {//OK
        }

    }

    @Test
    void testEqualityNeg() {
        Assertions.assertNotEquals(trtest1, trtest2);
    }

    @Test
    void testEquality1() {
        var trtestEq1 = new Triangle(3.0, 4.0, 5.0);
        var trtestEq2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertEquals(trtestEq1, trtestEq2);
    }
    @Test
    void testEquality2() {
        var trtestEq1 = new Triangle(5.0, 4.0, 3.0);
        var trtestEq2 = new Triangle(4.0, 3.0, 5.0);
        Assertions.assertTrue(trtestEq1.equals(trtestEq2));
    }
}
