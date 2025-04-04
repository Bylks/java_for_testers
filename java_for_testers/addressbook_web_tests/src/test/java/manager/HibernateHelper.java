package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
   private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertList (List<GroupRecord> records)
    {
        List<GroupData> result = new ArrayList<>();
        for (var record:records)
        {
            result.add(convert(record));
        }
        return result;
    }
    static List<ContactData> convertContactList (List<ContactRecord> records)
    {
        List<ContactData> result = new ArrayList<>();
        for (var record:records)
        {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData record) {
        var x =  record.id();
        x = x.isEmpty() ? "0": x;
        return new GroupRecord(Integer.parseInt(x), record.name(), record.header(), record.footer());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withChangedId("" + record.id)
                .withChangedFirstName(record.firstname)
                .withChangedLastName(record.lastname)
                .withChangedAddress(record.address);
    }

    private static ContactRecord convert(ContactData record) {
        var x =  record.id();
        x = x.isEmpty() ? "0": x;
        return new ContactRecord(Long.parseLong(x), record.firstName(), record.lastName(), record.address());
    }

    public List<ContactData> getContactList() {

        return sessionFactory.fromSession(session ->
        {
            return convertContactList(session.createQuery("from ContactRecord",ContactRecord.class).list());
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session ->
        {
            return (session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult());
        });
    }

    public List<GroupData> getGroupList() {

      return sessionFactory.fromSession(session ->
        {
            return convertList(session.createQuery("from GroupRecord",GroupRecord.class).list());
        });
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session ->
        {
            return (session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult());
        });
    }

    public void createGroup(GroupData groupData) {
         sessionFactory.inSession(session ->
         {
             session.getTransaction().begin();
             session.persist(convert(groupData));
             session.getTransaction().commit();
         });

    }

    public void createContact(ContactData contact) {
        sessionFactory.inSession(session ->
        {
            session.getTransaction().begin();
            session.persist(convert(contact));
            session.getTransaction().commit();
        });

    }

    public List<ContactData> getContactListInGroup(GroupData group) {
        return sessionFactory.fromSession(session ->
        {
           return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }


    public ContactData provideContactWithoutGroup() {
        var contacts = getContactList();
        for (var contact : contacts)
        {
               if (!isContactInAnyGroup(contact)) {
                   return contact;
               }
        }
        createContact(new ContactData()
                .withChangedFirstName("firstnameforinclude")
                .withChangedLastName("lastnameforinclude")
                .withChangedAddress("addressforinclude"));
        return getContactList().getLast();

    }

    private boolean isContactInAnyGroup(ContactData contact) {

        return sessionFactory.fromSession(session -> {
            String hql = "SELECT COUNT(g) > 0 FROM GroupRecord g JOIN g.contacts c WHERE c.id = :contactId";
            return session.createQuery(hql, Boolean.class)
                    .setParameter("contactId", contact.id())
                    .uniqueResult();
        });

    }

    public GroupData provideGroup() {
        if (getGroupCount() == 0) {
            createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        return getGroupList().getLast();
    }
}