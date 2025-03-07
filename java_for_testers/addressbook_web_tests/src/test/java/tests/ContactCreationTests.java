package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;


public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateAnyContract(){
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",""));
    }

    @Test
    public void canCreateContractWithGroup(){
        app.groups().createGroup(new GroupData().withChangedName("groupforcontractcreation"));
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test","groupforcontractcreation"));
    }
    @Test
    public void canCreateContractWithEmptyFields(){
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData());
    }
}
