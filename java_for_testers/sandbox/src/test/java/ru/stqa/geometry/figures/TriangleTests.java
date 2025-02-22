package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    //Почему то при запуске теста ругается так
    // Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.
    //Но тесты проходят успешно
    // При запуске gradlew.bat с --warning-mode all пишет The automatic loading of test framework implementation dependencies has been deprecated. This is scheduled to be removed in Gradle 9.0. Declare the desired test framework directly on the test suite or explicitly declare the test framework implementation dependencies on the test's runtime classpath.
    //Подскажете в чем может быть причина ?
    Triangle trtest1 = new Triangle(3.0,4.0,5.0);
    Triangle trtest2 = new Triangle(6.0,8.0,10.0);
    Triangle trtest3 = new Triangle(10.0,12.0,15.0);

    @Test
    void canCalculateTriangleAreaTest1()
    {
        Assertions.assertEquals(6,trtest1.calculateTriangeArea());
    }
    @Test
    void canCalculateTriangleAreaTest2()
    {
        Assertions.assertEquals(24,trtest2.calculateTriangeArea());
    }
    @Test
    void canCalculateTriangleAreaTest3()
    {
        Assertions.assertEquals(59.81168364124187,trtest3.calculateTriangeArea());
    }
    @Test
    void canCalculateTrianglePerimeterTest1()
    {
        Assertions.assertEquals(12,trtest1.calculateTriangePerimeter());
    }
    @Test
    void canCalculateTrianglePerimeterTest2()
    {
        Assertions.assertEquals(24,trtest2.calculateTriangePerimeter());
    }
    @Test
    void canCalculateTrianglePerimeterTest3()
    {
        Assertions.assertEquals(24,trtest3.calculateTriangePerimeter());
    }
}
