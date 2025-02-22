package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea()
    {
        Assertions.assertEquals(16, Square.squareArea(4));
    }
}
