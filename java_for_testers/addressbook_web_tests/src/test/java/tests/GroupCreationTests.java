package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<GroupData>(List.of(new GroupData().withChangedName("group name'")));
    }

    public static List<GroupData> groupProvider() {
       var result = new ArrayList<GroupData>();
        for (var name : List.of ("","name"))
        {
            for (var header : List.of ("","header"))
            {
                for (var footer : List.of ("","footer"))
                {
                    result.add(new GroupData().withChangedName(name).withChangedHeader(header).withChangedFooter(footer));
                }
            }
        }
//        for (int i = 0;i<5;i++)
//        {
//            result.add(new GroupData().withChangedName(randomString(i*10))
//                    .withChangedHeader(randomString(i*10))
//                    .withChangedFooter(randomString(i*10)));
//        }
      //  result.add(new GroupData());
      //  result.add(new GroupData().withChangedName("qwe"));
        return result;
    }
    @ParameterizedTest
    @MethodSource ("groupProvider")
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withChangedId(newGroups.get(newGroups.size() - 1).id()).withChangedHeader("").withChangedFooter(""));
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList,newGroups);
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups,newGroups);
    }

}
