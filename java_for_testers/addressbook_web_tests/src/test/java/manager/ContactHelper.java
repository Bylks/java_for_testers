package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
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
    click(By.xpath("//input[@value='Delete']"));
    }

    public void deleteContact(ContactData contact)
    {
      openHomePage();
      selectContactCheckbox(contact);
      submitDeletionContact();
    }

    private void selectContactCheckbox(ContactData contact) {
        By checkbox = By.cssSelector(String.format("input[type=\"checkbox\"][id=\"%s\"]", contact.id())); // input[type="checkbox"][id="50"]
        click(checkbox);
    }

    public void createContact(ContactData contactData)
    {
        openAddContactPage();
        fillContactValues(contactData);
        submitNewContact();
        openContactPage();
    }

    public void createContact(ContactData contactData, GroupData group)
    {
        openAddContactPage();
        fillContactValues(contactData);
        selectGroup(group);
        submitNewContact();
        openContactPage();
    }

    private void selectGroup(GroupData group) {
        selectOptionByValue(By.name("new_group"), group.id());
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void submitDeletionContact() {
        click(By.cssSelector("input[type=\"button\"][value=\"Delete\"]"));
    }

    private void submitNewContact() {
        click(By.name("submit"));
    }

    private void submitUpdateContact() {
        click(By.name("update"));
    }

    private void returnToContactsPage() {
        click(By.linkText("home page"));
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
      // if (!(contactData.group().isEmpty())){selectOption(By.name("new_group"),contactData.group());}
       if (!(contactData.photo().isEmpty())){attach(By.name("photo"),contactData.photo());}
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

    public List<ContactData> getList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr :trs)
        {
            var tds = tr.findElements(By.tagName("td"));
            var checkbox = tr.findElement(By.cssSelector("input[type='checkbox']"));
            String id = checkbox.getAttribute("id");
            String lastName = tds.get(1).getText();
            String firstName = tds.get(2).getText();
          //  var checkbox = span.findElement(By.name("selected[]"));
          //  var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withChangedId(id).withChangedFirstName(firstName).withChangedLastName(lastName));
        }
        return contacts;
    }

    public void editContact(ContactData contactData, ContactData editedContact) {
        openContactPage();
        initContractModification(contactData);
        fillContactValues(editedContact);
        submitUpdateContact();
        returnToContactsPage();

    }
    private void initContractModification(ContactData contactData) {
click(By.cssSelector("a[href='edit.php?id=" + contactData.id()+ "']"));
    }

    public void includeContactInGroup(ContactData contactData, GroupData groupData) {
        refreshContactPage();
        selectContactCheckbox(contactData);
        selectGroupToInclude(groupData);
        submitIncludeContactInGroup();
        openContactPage();
    }

    private void submitIncludeContactInGroup() {
        click(By.name("add"));
    }

    private void selectGroupToInclude(GroupData group) {
        selectOptionByValue(By.name("to_group"), group.id());
    }
    public void refreshContactPage()
    {
            click(By.linkText("home"));

    }
}
