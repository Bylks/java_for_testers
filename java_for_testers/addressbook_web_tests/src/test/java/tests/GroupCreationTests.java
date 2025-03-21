package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @ParameterizedTest
    @MethodSource ("groupProvider")
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
         var newGroups = app.groups().getList(); // в некоторых случаях в newGroups элементы получаются не отсортированы, из за этого newGroups.get(newGroups.size() - 1) - не указывает на последний добавленный элемент
        var expectedList = new ArrayList<>(oldGroups);
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare((Integer.parseInt(o1.id())), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById); // сначала сортируем а потому получаем newGroups.get(newGroups.size() - 1)
        expectedList.add(group.withChangedId(newGroups.get(newGroups.size() - 1).id()).withChangedHeader("").withChangedFooter("")); // ??
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
