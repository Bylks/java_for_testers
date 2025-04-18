package ru.stqua.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.UserApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.AccessLevel;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import io.swagger.client.model.UserResponse;
import ru.stqua.mantis.model.UserData;

public class RestApiHelper extends HelperBase{

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public boolean isLoggedIn(UserData user) { // не похоже что через апи можно получить ключ авторизации из-под администратора
        UsersApi apiInstance = new UsersApi();
        try {
            UserResponse result = apiInstance.userGetMe();
            System.out.println(result);
            return true;
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userGetMe");
            e.printStackTrace();
        }
        return false;
    }

    public void createUser(UserData userdata) {
        User user = new User();// User | The user to add.
        var acceesLevel = new AccessLevel();
        acceesLevel.id(userdata.accesslevel());
        user.setAccessLevel(acceesLevel);
        user.setEmail(userdata.email());
        user.setUsername(userdata.username());
        UserApi apiInstance = new UserApi();
        try {
            UserAddResponse result = apiInstance.userAdd(user);
            //System.out.println(result);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
