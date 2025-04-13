package ru.stqua.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqua.mantis.common.CommonFunctions;

public class JamesTests extends TestBase
{
    @Test
    public void canCreateUser()
        {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(5)),"password");
    }
}
