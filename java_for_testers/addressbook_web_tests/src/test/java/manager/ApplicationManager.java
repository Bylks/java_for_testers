package manager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    public void init(String browser) {
        if (driver == null) {
            switch (browser)
            {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                default: throw new IllegalArgumentException(String.format("Browser %s is not supported", browser));
            }
            Runtime.getRuntime().addShutdownHook((new Thread(driver::quit)));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(2560, 1400));
            session().login("admin", "secret");
        }
    }
    public GroupHelper groups()
    {
        if (groups == null){
            groups = new GroupHelper(this);
        }
        return groups;
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
