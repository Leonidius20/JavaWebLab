package ua.leonidius.javaweblab;

import java.io.File;

public class DirectoryProcessor implements Runnable {

    private final File directory;

    public DirectoryProcessor(File directory) {
        this.directory = directory;
    }

    @Override
    public void run() {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                new Thread(new DirectoryProcessor(file)).start();
            } else {
                if (Utils.getFileExtension(file).equalsIgnoreCase("java"))
                    new Thread(new FileProcessor(file)).start(); // TODO: executors!
            }
        }

    }

}
