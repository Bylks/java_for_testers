package ru.stqua.mantis.manager;

import okhttp3.OkHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class HelperBase {
    protected final ApplicationManager manager;
    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }
    protected void attach(By locator, String file) {
        manager.driver().findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        manager.driver().findElement(locator).click();
    }
    protected void fillDate(By locatorDay, By locatorMonth, By locatorYear, LocalDate date) {
        selectOption(locatorDay, date.format(DateTimeFormatter.ofPattern("d")));
        selectOption(locatorMonth, date.format(DateTimeFormatter.ofPattern("MMMM", Locale.forLanguageTag("en"))));
        type(locatorYear, date.format(DateTimeFormatter.ofPattern("yyyy")));
    }

    protected void selectOption(By select, String option) {
        click(select);
        WebElement dropdown = manager.driver().findElement(select);
        new Select(dropdown).selectByVisibleText(option);

      //  WebElement dropdown = manager.driver.findElement(select);
      // dropdown.findElement(By.xpath("//option[. = '"+option+"']")).click(); почемуто не работает
    }

    protected void selectOptionByValue(By select, String value) {
        click(select);
        WebElement dropdown = manager.driver().findElement(select);
        new Select(dropdown).selectByValue(value);
    }

    protected void goHomePage() {
        click(By.linkText("home page"));
    }

    protected boolean isElementPresent (By locator)
    {
       return (manager.driver().findElements(locator).size()>0);
    }
}
