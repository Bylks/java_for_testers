package tests;
import model.GroupData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {
  @Test
  public void editGroupTest() {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
      // app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
    }
    var oldGroups = app.hbm().getGroupList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());
    var editedGroup = new GroupData().withChangedHeader("Edited header").withChangedName("Edited Name").withChangedFooter("Edited Footer");
    app.groups().editGroup(oldGroups.get(index), editedGroup);
    var newGroups = app.hbm().getGroupList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index, editedGroup.withChangedId(oldGroups.get(index).id()));
    Comparator<GroupData> compareById = (o1, o2) ->
    {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
    newGroups.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newGroups, expectedList);
  }

}
