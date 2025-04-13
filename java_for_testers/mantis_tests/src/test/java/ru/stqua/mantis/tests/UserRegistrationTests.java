package ru.stqua.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

import static ru.stqua.mantis.common.CommonFunctions.randomString;

public class UserRegistrationTests extends TestBase{

    public static List<String> singleUserProvider() throws IOException {
        return  List.of(randomString(7));
    }

    @ParameterizedTest
    @MethodSource("singleUserProvider")
    public void canRegisterUser(String username)
    {
        var email = String.format("%s@localhost",username);
 //       app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(5)),"password");
        app.jamesCli().addUser(email,"password"); //создать почту
        app.mantis().createUser(username,email); // создать пользователя mantis
        var url = app.mail().extractUrlFromLastMail(email, "password"); //ждать и получить почту и ссылку из письма
       // app.http().useHttpUrl(url);// переход по ссылке
        app.mantis().activateAccount(url);
       // app.http().activateAccount(url);
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());//проверяем логин
    }
}
