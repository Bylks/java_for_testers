package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager){
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try  (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("SELECT group_id,group_name, group_header, group_footer FROM GROUP_LIST"))
        {
        while (result.next())
        {
            groups.add(new GroupData()
                    .withChangedId(result.getString("group_id"))
                    .withChangedFooter(result.getString("group_footer"))
                    .withChangedHeader(result.getString("group_header"))
                    .withChangedName(result.getString("group_name")));

        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkconsistency() {
        try  (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("SELECT * from `address_in_groups` a left join addressbook b on a.id = b.id where b.id is NULL"))
        {
            if (result.next())
            {
                throw new IllegalStateException("db is corrupted");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//DELETE ag FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id - ag.id where ab.id is NULL;
public boolean isContactIncludedInGroup(ContactData contactData, GroupData groupData) {
    // var result = statement.executeQuery(String.format("SELECT * FROM `address_in_groups` WHERE id = %s AND group_id = %s", contactData.id(), groupData.id()));
    try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "")) {
        try (var statement = conn.createStatement()) {
            try (var result = statement.executeQuery(String.format("SELECT * FROM `address_in_groups` WHERE id = %s AND group_id = %s", contactData.id(), groupData.id()))) {
                return result.next();
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public void includeContactInGroup(ContactData contactData, GroupData groupData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdDate = dateFormat.format(new Date());
        String modifiedDate = dateFormat.format(new Date());

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement()) {

            // Формируем SQL-запрос
            String query = String.format("INSERT INTO `address_in_groups` (`domain_id`, `id`, `group_id`, `created`, `modified`, `deprecated`) VALUES (0, %s, %s, '%s', '%s', '0000-00-00 00:00:00');",
                    contactData.id(), groupData.id(), createdDate, modifiedDate);

            // Выполняем запрос
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
