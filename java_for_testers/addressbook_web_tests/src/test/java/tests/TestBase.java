package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if(app==null)
        {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target","local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser","firefox"), properties);
        }

    }
    @AfterEach
    public void checkDBConsistency(){
        app.jdbc().checkconsistency();
        //  select * from `address_in_groups` a left join addressbook b on a.id = b.id where b.id is NULL;
    }

}
