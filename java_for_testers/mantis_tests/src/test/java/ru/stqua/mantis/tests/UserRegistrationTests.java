package ru.stqua.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqua.mantis.common.CommonFunctions;
import ru.stqua.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

public class UserRegistrationTests extends TestBase{

    public static List<UserData> singleUserProvider() throws IOException {
        var name = CommonFunctions.randomString(5);
        var realName = CommonFunctions.randomString(7);
        var email = String.format("%s@localhost",name);
        return  List.of(new UserData()
                .withUserName(name)
                .withAccessLevel(25L)
                .withEmail(email)
                .withPassword("password")
                .withRealName(realName));
    }

    @ParameterizedTest
    @MethodSource("singleUserProvider")
    public void canRegisterUser(UserData user)
    {
        var email = String.format("%s@localhost",user.username());
 //       app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(5)),"password");
        app.jamesCli().addUser(email,"password"); //создать почту
        app.mantis().createUser(user.username(),email); // создать пользователя mantis
        var url = app.mail().extractUrlFromLastMail(email, "password"); //ждать и получить почту и ссылку из письма
       // app.http().useHttpUrl(url);// переход по ссылке
        app.mantis().activateAccount(url,user );
       // app.http().activateAccount(url);
        app.http().login(user.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());//проверяем логин
    }

@Test
    public void canRegisterUserApi()
    {
        var name = CommonFunctions.randomString(5);
        var realName = CommonFunctions.randomString(7);
        var email = String.format("%s@localhost",name);
        var user= new UserData()
                .withUserName(name)
                .withAccessLevel(25L)
                .withEmail(email)
                .withPassword("password")
                .withRealName(realName);

        app.jamesApi().addUser(email,"password"); //создать почту
        app.rest().createUser(user);
        var url = app.mail().extractUrlFromLastMail(email, "password"); //ждать и получить почту и ссылку из письма
        app.mantis().activateAccount(url,user);
       app.http().login(user.username(), user.password());
         Assertions.assertTrue(app.http().isLoggedIn());//проверяем логин
    }
}
