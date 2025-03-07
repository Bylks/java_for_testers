package tests;
import model.GroupData;
import org.junit.jupiter.api.*;

public class GroupCreationTests extends TestBase {


    @Test
    public void canCreateGroup() {
        GroupData groupdata = new GroupData("group name", "group header", "group footer");
        app.groups().createGroup(groupdata);
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withChangedName("changedgroupname"));
    }
    @Test
    public void canCreateGroupWithNameAndHeaderOnly() {
        app.groups().createGroup(new GroupData().withChangedName("changedgroupname1").withChangedHeader("Header1"));
    }
}
