package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;


public class GroupDeletionTests extends TestBase {
    @Test
    public void testDeleteGroup() {
        app.groups().openGroupsPage();
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }


}
