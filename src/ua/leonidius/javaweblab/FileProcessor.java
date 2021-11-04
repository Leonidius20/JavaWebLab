package ua.leonidius.javaweblab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessor implements Runnable {

    private final File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println(file.getName());

        try {
            var content = Files.readString(file.toPath());

            // \b matches whitespace before and after the word (0 length)
            // \w is word {4, } is 4 and more chars
            Matcher m = Pattern.compile("\\b\\w{4,}\\b").matcher(content);

            var sb = new StringBuilder();
            while (m.find()) {
                m.appendReplacement(sb, m.group().toUpperCase()); // group number 0 is the whole regex
            }
            m.appendTail(sb);

            Files.writeString(file.toPath(), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
