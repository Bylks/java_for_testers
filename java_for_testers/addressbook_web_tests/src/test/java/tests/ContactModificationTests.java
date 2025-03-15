package tests;
import model.ContactData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void editContactTest() {

       if (app.contacts().getCount()==0)
      {
          app.contacts().createContact(new ContactData().withChangedFirstName("fname for edit test")
                  .withChangedLastName("lname for edit test"));
      }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var editedContact = new ContactData().withChangedFirstName("Edited fname")
                .withChangedLastName("Edited lname");
        app.contacts().editContact(oldContacts.get(index), editedContact);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, editedContact.withChangedId(oldContacts.get(index).id())); //  убираем из проверки все кроме Id и имени т.к. со страницы из getList() можем получить только это
        Comparator<ContactData> compareById = (o1, o2) ->
       {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

}