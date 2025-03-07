package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void canDeleteAnyContract(){
        app.contacts().openContactPage();
        if (!(app.contacts().isContactPresent()))
        {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteAnyContact();
        app.contacts().openContactPage();
    }
}
