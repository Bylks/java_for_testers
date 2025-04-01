package tests;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    public void editContactTest() {

       if (app.hbm().getContactCount()==0)
      {
          app.hbm().createContact(new ContactData()
                  .withChangedFirstName("fname for edit test")
                  .withChangedLastName("lname for edit test")
                  .withChangedAddress("address for edit test"));
      }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var editedContact = new ContactData().withChangedFirstName("Edited fname")
                .withChangedLastName("Edited lname")
                .withChangedAddress("Edited address");
        app.contacts().editContact(oldContacts.get(index), editedContact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, editedContact.withChangedId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) ->
       {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void includeContactInGroupTest() {
        int indexc = 0;
        int indexg = 0;
        boolean somethingWasCreated = false;
        boolean found = false;
        var rnd = new Random();
        if (app.hbm().getContactCount()==0)
        {
            app.hbm().createContact(new ContactData()
                    .withChangedFirstName("firstnameforinclude")
                    .withChangedLastName("lastnameforinclude")
                    .withChangedAddress("addressforinclude")
            );
            somethingWasCreated = true;
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            somethingWasCreated = true;
        }

        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();
        if (somethingWasCreated) // Если чтото создавали то связки не должно быть, берем случайные
        {
           indexc = rnd.nextInt(contacts.size());
           indexg = rnd.nextInt(groups.size());
            app.contacts().includeContactInGroup(contacts.get(indexc), groups.get(indexg));
        } else { // Если ничего не создавали ищем отсутствующую связку если находим, добавляем
            for (var contact : contacts)
            {
                for (var group : groups)
                {
                    if (!app.jdbc().isContactIncludedInGroup(contact, group))
                    {
                        app.contacts().includeContactInGroup(contact, group);
                        Assertions.assertTrue(app.jdbc().isContactIncludedInGroup(contact, group));
                        found=true;
                        break;
                    }
                }
                if (found) {break;}
            }
            // Если все контакты уже в группах создаём контакт и добавляем в сущ группу
            app.hbm().createContact(new ContactData()
                    .withChangedFirstName("firstnameforincludeQQQww")
                    .withChangedLastName("lastnameforincludeQQQww")
                    .withChangedAddress("addressforincludeQQQww"));
            indexg = rnd.nextInt(groups.size());
            contacts = app.hbm().getContactList();
            app.contacts().includeContactInGroup(contacts.getLast(), groups.get(indexg));
            Assertions.assertTrue(app.jdbc().isContactIncludedInGroup(contacts.getLast(), groups.get(indexg)));
        }

    }

    @Test
    public void excludeContactInGroupTest() {

        if (app.hbm().getContactCount()==0)
        {
            app.hbm().createContact(new ContactData()
                    .withChangedFirstName("firstnameforinclude")
                    .withChangedLastName("lastnameforinclude")
                    .withChangedAddress("addressforinclude")
            );
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            // app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();
        var rnd = new Random();
        var indexc = rnd.nextInt(contacts.size());
        var indexg = rnd.nextInt(groups.size());
        app.jdbc().includeContactInGroup(contacts.get(indexc), groups.get(indexg));
      //  Assertions.assertTrue(app.jdbc().isContactIncludedInGroup(contacts.get(indexc),groups.get(indexg)));
        app.contacts().excludeContactInGroup(contacts.get(indexc), groups.get(indexg));
//        var contactsAfter = app.hbm().getContactList();
//        var groupsAfter = app.hbm().getGroupList();
        Assertions.assertFalse(app.jdbc().isContactIncludedInGroup(contacts.get(indexc),groups.get(indexg)));
    }



}