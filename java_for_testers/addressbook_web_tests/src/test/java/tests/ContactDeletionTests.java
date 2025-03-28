package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactDeletionTests extends TestBase {
    @Test
    public void canDeleteAnyContract(){
        app.contacts().openContactPage();
        if ((app.hbm().getContactCount()==0))
        {
            app.hbm().createContact(new ContactData());
        }
        var contactsListBefore = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contactsListBefore.size());
        app.contacts().deleteContact(contactsListBefore.get(index));
        var contactsListAfter = app.hbm().getContactList();
        //    int contactsCountAfter = app.contacts().getCount();
        var expectedList = new ArrayList<>(contactsListBefore);
        Comparator<ContactData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        contactsListAfter.sort(compareById);
        expectedList.remove(contactsListBefore.get(index));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList,contactsListAfter);
        app.contacts().openContactPage();
    }




}
