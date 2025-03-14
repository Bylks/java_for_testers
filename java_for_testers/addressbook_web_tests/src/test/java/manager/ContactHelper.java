package manager;
import model.ContactData;
import org.openqa.selenium.By;

import java.util.Objects;

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
        openContactPage();
    }

    private void submitNewContact() {
        click(By.name("submit"));
    }

    private void fillContactValues(ContactData contactData) {
       //String lastName,String firstName, String address, String email1, String homePhone, String group
       if (!contactData.firstName().isEmpty()) {type(By.name("firstname"),contactData.firstName());}
       if (!contactData.lastName().isEmpty()) {type(By.name("lastname"),contactData.lastName());}
       if (!contactData.address().isEmpty()) {type(By.name("address"),contactData.address());}
       if (!contactData.email1().isEmpty()) { type(By.name("email"),contactData.email1());}
       if (!contactData.homePhone().isEmpty()) { type(By.name("home"),contactData.homePhone());}
       if (Objects.nonNull(contactData.birthday())) { fillDate(By.name("bday"),By.name("bmonth"),By.name("byear"),contactData.birthday());}
       if (Objects.nonNull(contactData.anniversary())) { fillDate(By.name("aday"),By.name("amonth"),By.name("ayear"),contactData.anniversary());}
       if (!(contactData.group().isEmpty())){selectOption(By.name("new_group"),contactData.group());}
    }

    private void openAddContactPage() {
        if (!manager.isElementPresent(By.name("submit")))
        {
        click(By.linkText("add new"));
        }
    }

    public int getCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
