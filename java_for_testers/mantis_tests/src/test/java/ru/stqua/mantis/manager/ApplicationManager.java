package ru.stqua.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private Properties properties;
    private String browser;
    public SessionHelper sessionHelper;
    public void init(String browser, Properties properties) {
        this.properties = properties;
        this.browser = browser;
    }
    public WebDriver driver(){
        if (driver == null)
        {
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
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(2560, 1400));
        }
        return driver;
    }
    public SessionHelper session()
    {
        if (sessionHelper == null)
        {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }


}
