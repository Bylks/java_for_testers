package tests;
import model.GroupData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {
  @Test
  public void editGroupTest() {
    if (app.groups().getCount()==0)
    {
      app.groups().createGroup(new GroupData("", "qqqqq","qqqqq","qqqqq"));
    }
    var oldGroups = app.groups().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());
    var editedGroup = new GroupData().withChangedHeader("Edited header").withChangedName("Edited Name").withChangedFooter("Edited Footer");
    app.groups().editGroup(oldGroups.get(index), editedGroup);
    var newGroups = app.groups().getList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.set(index, editedGroup.withChangedId(oldGroups.get(index).id()).withChangedFooter("").withChangedHeader("")); // убираем из проверки все кроме Id и имени т.к. со страницы из getList() можем получить только это
    Comparator<GroupData> compareById = (o1, o2) ->
    {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
    newGroups.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newGroups, expectedList);
  }

}
