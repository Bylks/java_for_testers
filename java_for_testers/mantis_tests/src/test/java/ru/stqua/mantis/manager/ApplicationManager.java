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
    public HttpSessionHelper httpSessionHelper;
    public JamesCliHelper jamesCliHelper;
    public MailHelper mailHelper;
    public MantisHelper mantisHelper;
    public JamesApiHelper jamesApiHelper;
    public RestApiHelper restApiHelper;
    public void init(String browser, Properties properties) {
        this.properties = properties;
        this.browser = browser;
    }
    public String property(String name){
        return properties.getProperty(name);
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

    public HttpSessionHelper http()
    {
        if (httpSessionHelper == null)
        {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli()
    {
        if (jamesCliHelper == null)
        {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
    public MailHelper mail()
    {
        if (mailHelper == null)
        {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public MantisHelper mantis()
    {
        if (mantisHelper == null)
        {
            mantisHelper = new MantisHelper(this);
        }
        return mantisHelper;
    }

    public JamesApiHelper jamesApi()
    {
        if (jamesApiHelper == null)
        {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public RestApiHelper rest()
    {
        if (restApiHelper == null)
        {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }



}
