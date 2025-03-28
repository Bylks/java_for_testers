package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<GroupData>(List.of(new GroupData().withChangedName("group name'")));
    }

    public static List<GroupData> groupProvider() throws IOException {
       var result = new ArrayList<GroupData>();
//        for (var name : List.of ("","name"))
//        {
//            for (var header : List.of ("","header"))
//            {
//                for (var footer : List.of ("","footer"))
//                {
//                    result.add(new GroupData().withChangedName(name).withChangedHeader(header).withChangedFooter(footer));
//                }
//            }
//        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> singleGroupProvider() throws IOException {
     return  List.of(new GroupData()
                .withChangedName("Name" + CommonFunctions.randomString(10))
                .withChangedHeader("Header" + CommonFunctions.randomString(10))
                .withChangedFooter("Footer" + CommonFunctions.randomString(10)));
    }

    @ParameterizedTest
    @MethodSource ("singleGroupProvider") // groupProvider singleGroupProvider
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
 //       var oldGroups = app.jdbc().getGroupList();
//        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
 //       var newGroups = app.jdbc().getGroupList();
//         var newGroups = app.groups().getList();

        var expectedList = new ArrayList<>(oldGroups);
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById); // сначала сортируем а потому получаем newGroups.get(newGroups.size() - 1)
        var maxSize = newGroups.get(newGroups.size() - 1).id();
        expectedList.add(group.withChangedId(maxSize)); // ??
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList,newGroups);
        var newUiGroups = app.groups().getList();
        var expectedUiList = expectedList;
        expectedUiList.replaceAll(groupCh ->groupCh.withChangedFooter("").withChangedHeader(""));
        newUiGroups.sort(compareById);
        Assertions.assertEquals(newUiGroups,expectedUiList);
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
