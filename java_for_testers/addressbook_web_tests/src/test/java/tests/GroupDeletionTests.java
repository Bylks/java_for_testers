package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupDeletionTests extends TestBase {
    @Test
    public void testDeleteGroup() {

        app.groups().openGroupsPage();
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupsCountBefore = app.groups().getCount();
        app.groups().removeGroup();
        int groupsCountAfter = app.groups().getCount();
        Assertions.assertEquals(groupsCountBefore-1,groupsCountAfter);
    }

    @Test
    public void testDeleteAllGroupsAtOnce() {

        app.groups().openGroupsPage();
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }


}
