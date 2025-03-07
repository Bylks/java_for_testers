package manager;
import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }
    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void openContactPage()
    {
        if (!manager.isElementPresent(By.name("add"))) {
            click(By.linkText("home"));
        }
    }
    public void deleteAnyContact()
    {
    click(By.name("selected[]"));
    click(By.xpath("//input[@value=\'Delete\']"));
    }
    public void createContact(ContactData contactData)
    {
        openAddContactPage();
        fillContactValues(contactData);
        submitNewContact();
        goHomePage();
    }

    private void goHomePage() {
        click(By.linkText("home page"));
    }

    private void submitNewContact() {
        click(By.name("submit"));
    }

    private void fillContactValues(ContactData contactData) {
       //String lastName,String firstName, String address, String email1, String homePhone, String group
        type(By.name("firstname"),contactData.firstName());
        type(By.name("lastname"),contactData.lastName());
        type(By.name("address"),contactData.address());
        type(By.name("email"),contactData.email1());
        type(By.name("home"),contactData.homePhone());
        if (!(contactData.group().isEmpty()))
        {
            selectOption("new_group",contactData.group());
        }
        //tut poprobovat group v base helper dobavit rabotu s vipadaushim spiskom na vhod stroka ?
    }

    private void selectOption(String select, String option) {
        click(By.name(select));
        WebElement dropdown = manager.driver.findElement(By.name(select));
        dropdown.findElement(By.xpath("//option[. = '"+option+"']")).click();
    }

    private void openAddContactPage() {
        if (!manager.isElementPresent(By.name("submit")))
        {
        click(By.linkText("add new"));
        }
    }

}
