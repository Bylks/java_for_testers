package manager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;
    private HibernateHelper hbm;
    private Properties properties;
    private JdbcHelper jdbc;
    public void init(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        if (driver == null) {
            var seleniumServer = properties.getProperty("seleniumServer");
                switch (browser) {
                    case "firefox":
                        if (seleniumServer != null) {driver = new RemoteWebDriver(new URL(seleniumServer), new FirefoxOptions());}
                        else {driver = new FirefoxDriver();}
                        break;
                    case "chrome":
                        if (seleniumServer != null) {driver = new RemoteWebDriver(new URL(seleniumServer), new ChromeOptions());}
                        else {driver = new ChromeDriver();}
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Browser %s is not supported", browser));
                }
                Runtime.getRuntime().addShutdownHook((new Thread(driver::quit)));
                driver.get(properties.getProperty("web.baseUrl"));
                driver.manage().window().setSize(new Dimension(2560, 1400));
                session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));

        }
    }

    public JdbcHelper jdbc()
    {
        if (jdbc == null){
            jdbc = new JdbcHelper(this);
        }
        return jdbc;
    }

    public HibernateHelper hbm()
    {
        if (hbm == null){
            hbm = new HibernateHelper(this);
        }
        return hbm;
    }

    public GroupHelper groups()
    {
        if (groups == null){
            groups = new GroupHelper(this);
        }
        return groups;
    }
    public ContactHelper contacts()
    {
        if (contacts == null){
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    public LoginHelper session()
    {
        if (session == null){
            session = new LoginHelper(this);
        }
        return session;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

}
