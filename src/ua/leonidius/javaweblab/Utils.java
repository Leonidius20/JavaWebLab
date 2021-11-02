package ua.leonidius.javaweblab;

import java.io.File;

public abstract class Utils {

    public static String getFileExtension(File file) {
        String[] splitName = file.getName().split("\\.");
        return splitName[splitName.length - 1];
    }

}
