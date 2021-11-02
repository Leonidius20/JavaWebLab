package ua.leonidius.javaweblab;

import java.io.File;

public class FileProcessor implements Runnable {

    private final File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println(file.getName());
    }

}
