package generator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-n"})
    Integer count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        switch (format) {
            case "json" -> {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(new File(output), data);
            }
            case "yaml" -> {
                var mapper = new YAMLMapper();
                mapper.writeValue(new File(output), data);
            }
            case "xml" -> {
                var mapper = new XmlMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(new File(output), data);
            }
            default -> throw new IllegalArgumentException("Неизвестный формат" + format);
        }
    }


    private Object generate()
    {
        if(type.equals("groups")){ return generateGroups();}
        else if (type.equals("contacts")) {return generateContacts();}
        else {throw new IllegalArgumentException("Неизвестный тип"+type); }


    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0;i<count;i++)
        {
            result.add(new ContactData().withChangedFirstName("firstname"+ CommonFunctions.randomString(i*2))
                    .withChangedLastName("lastname"+ CommonFunctions.randomString(i*2))
                    .withChangedAddress("address"+ CommonFunctions.randomString(i*2))
             //       .withPhoto("src/test/resources/images/1.png"));
              .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))); //,,randomString(i*10)));
        }
        return result;
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0;i<count;i++)
        {
            result.add(new GroupData().withChangedName(CommonFunctions.randomString(i*10))
                    .withChangedHeader(CommonFunctions.randomString(i*10))
                    .withChangedFooter(CommonFunctions.randomString(i*10)));
        }
        return result;
    }
}
