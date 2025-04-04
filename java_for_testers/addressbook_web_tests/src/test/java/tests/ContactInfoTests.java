package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;
public class ContactInfoTests extends TestBase {

    @Test
    void testPhones()
    {
        var contact = app.hbm().provideContactWithPhone();
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.homePhone(),contact.mobilePhone(), contact.workPhone(),contact.phone2Phone())
                .filter(s -> s != null && !s.equals(""))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,phones);
    }
    @Test
    void testAddress()
    {
        var contact = app.hbm().provideContactWithAddress();
        var address = app.contacts().getAddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected,address);
    }

    @Test
    void testEmails()
    {
        var contact = app.hbm().provideContactWithEmails();
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email1(),contact.email2(),contact.email3())
                .filter(s -> s != null && !s.equals(""))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,emails);
    }
}




