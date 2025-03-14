package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of ("","firstname1"))
        {
            for (var lastName : List.of ("","lastName1"))
            {
                for (var address : List.of ("","address1"))
                {
                    result.add(new ContactData().withChangedFirstName(firstname).withChangedLastName(lastName).withChangedAddress(address));
                }
            }
        }
        for (int i = 0;i<5;i++)
        {
            result.add(new ContactData().withChangedFirstName("firstname"+ randomString(i*2))
                    .withChangedLastName("lastname"+ randomString(i*2))
                    .withChangedAddress("address"+randomString(i*2))); //,,randomString(i*10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContracts(ContactData contact) {
        int contactsCountBefore = app.contacts().getCount();
        app.contacts().createContact(contact);
        int contactsCountAfter = app.contacts().getCount();
        Assertions.assertEquals(contactsCountBefore+1,contactsCountAfter);
    }

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
        app.groups().createGroup(new GroupData().withChangedName("groupforcontractcreation"));
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",
                LocalDate.parse("1234-12-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2005-01-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")),"groupforcontractcreation"));
    }
}
