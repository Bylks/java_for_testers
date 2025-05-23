package ru.stqua.mantis.manager;

import org.openqa.selenium.By;
import ru.stqua.mantis.model.UserData;

public class MantisHelper extends HelperBase{
    public MantisHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(String username, String email)
    {
        openLoginPage();
        type(By.name("username"),username); // type in <input id="username" name="username"
        type(By.name("email"),email); // type in <input id="email-field" name="email"
        click(By.cssSelector("input[type='submit']")); //click <input type="submit"
        proceed();
    }

    private void openLoginPage() {
        click(By.cssSelector("a[href='signup_page.php']"));  //"signup_page.php"
    }
    private void proceed() {
        click(By.cssSelector("a[href='login_page.php']"));  //"signup_page.php"
    }



    public void getUsingUrl(String url)
    {
        manager.driver().get(url);
    }

    public void activateAccount(String url, UserData user)
    {
        getUsingUrl(url);
        fillActivationForm(user);
        updateUser();
    }

    private void updateUser()
    {
        click(By.cssSelector("button[type='submit']"));
    }

    private void fillActivationForm(UserData user)
    {
        type(By.name("realname"),user.realname());
        type(By.name("password"),user.password());
        type(By.name("password_confirm"),user.password());
    }
}
