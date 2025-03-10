package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateAnyContract(){
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",null,null,""));
    }

    @Test
    public void canCreateContractWithGroup(){
        app.groups().createGroup(new GroupData().withChangedName("groupforcontractcreation"));
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",
                null,
                null,"groupforcontractcreation"));
    }
    @Test
    public void canCreateContractWithEmptyFields(){
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData());
    }
    @Test
    public void CanCreateContractWithDates() {
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",
                LocalDate.parse("1234-12-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2005-01-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")),"groupforcontractcreation"));
    }
}
