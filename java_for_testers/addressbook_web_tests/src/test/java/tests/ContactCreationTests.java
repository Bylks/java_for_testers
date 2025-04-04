package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of ("","firstname1"))
//        {
//            for (var lastName : List.of ("","lastName1"))
//            {
//                for (var address : List.of ("","address1"))
//                {
//                    result.add(new ContactData().withChangedFirstName(firstname).withChangedLastName(lastName).withChangedAddress(address));
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContracts(ContactData contact) {
        var contactsListBefore = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var contactsListAfter = app.hbm().getContactList();
        var expectedList = new ArrayList<>(contactsListBefore);
        Comparator<ContactData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        contactsListAfter.sort(compareById);
        expectedList.add(contact.withChangedId(contactsListAfter.get(contactsListAfter.size() - 1).id()).truncateAllBesidesNamesIdAddress()); // ??
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList,contactsListAfter);
    }
//    @Test
//    public void canCreateContractWithGroup(){
//        app.groups().createGroup(new GroupData().withChangedName("groupforcontractcreation"));
//        app.contacts().openContactPage();
//        app.contacts().createContact(new ContactData("", "Last Name Test","First Name Test",
//                "Address Test","Email1 Test","Home Phone Test",
//                null,
//                null, ""));
//    }
    @Test
    public void CanCreateContractWithDates() {
        app.groups().createGroup(new GroupData().withChangedName("groupforcontractcreation"));
        app.contacts().openContactPage();
        app.contacts().createContact(new ContactData("", "Last Name Test","First Name Test",
                "Address Test","Email1 Test","Home Phone Test",
                LocalDate.parse("1234-12-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2005-01-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "", "", "", "", "", ""));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContractInGroup(ContactData contact) {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            // app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var contactsListBefore = app.hbm().getContactListInGroup(group);
        app.contacts().createContact(contact, group);
        var contactsListAfter = app.hbm().getContactListInGroup(group);
        var expectedList = new ArrayList<>(contactsListBefore);
        Comparator<ContactData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        contactsListAfter.sort(compareById);
        expectedList.add(contact.withChangedId(contactsListAfter.get(contactsListAfter.size() - 1).id()).truncateAllBesidesNamesIdAddress());
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList,contactsListAfter);
    }


}
