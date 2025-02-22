package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    //Почему то при запуске теста ругается так
    // Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.
    //Но тесты проходят успешно
    // При запуске gradlew.bat с --warning-mode all пишет The automatic loading of test framework implementation dependencies has been deprecated. This is scheduled to be removed in Gradle 9.0. Declare the desired test framework directly on the test suite or explicitly declare the test framework implementation dependencies on the test's runtime classpath.
    //Подскажете в чем может быть причина ?
    @Test
    void canCalculateTriangleAreaTest1()
        {
        Triangle trtest = new Triangle(3.0,4.0,5.0);
        Assertions.assertEquals(6,trtest.calculateTriangeArea());
    }
    @Test
    void canCalculateTriangleAreaTest2()
    {
        Triangle trtest = new Triangle(6.0,8.0,10.0);
        Assertions.assertEquals(24,trtest.calculateTriangeArea());
    }
    @Test
    void canCalculateTriangleAreaTest3()
    {
        Triangle trtest = new Triangle(10.0,12.0,15.0);
        Assertions.assertEquals(59.81168364124187,trtest.calculateTriangeArea());
    }
    @Test
    void canCalculateTrianglePerimeterTest1()
    {
        Triangle trtest = new Triangle(3.0,4.0,5.0);
        Assertions.assertEquals(12,trtest.calculateTriangePerimeter());
    }
    @Test
    void canCalculateTrianglePerimeterTest2()
    {
        Triangle trtest = new Triangle(6.0,8.0,10.0);
        Assertions.assertEquals(24,trtest.calculateTriangePerimeter());
    }
}
