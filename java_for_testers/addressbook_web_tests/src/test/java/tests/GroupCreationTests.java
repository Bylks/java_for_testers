package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
                    result.add(new GroupData(name,header,footer));
                }
            }
        }
        for (int i = 0;i<5;i++)
        {
            result.add(new GroupData(randomString(i*10),randomString(i*10),randomString(i*10)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource ("groupProvider")
    public void canCreateMultipleGroup(GroupData group) {
        int groupCountBefore = app.groups().getCount();
        app.groups().createGroup(group);
        int groupCountAfter = app.groups().getCount();
        Assertions.assertEquals(groupCountBefore+1,groupCountAfter);
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        int groupCountBefore = app.groups().getCount();
        app.groups().createGroup(group);
        int groupCountAfter = app.groups().getCount();
        Assertions.assertEquals(groupCountBefore,groupCountAfter);
    }

}
