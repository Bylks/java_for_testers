package ru.stqua.mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n)
    {
        var rnd = new Random();
       var result = "";
       for (int i = 0;i<n;i++)
       {
           result += (char)('a'+rnd.nextInt(26));
       }
       return result;
    }

    public static String randomFile(String dir)
    {
       var filenames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(filenames.length);
        return Paths.get(dir, filenames[index]).toString();

    }
}
