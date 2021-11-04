package ua.leonidius.javaweblab;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DirectoryProcessor implements Runnable {

    private final File directory;
    private final ExecutorService executor;

    public DirectoryProcessor(File directory, ExecutorService executor) {
        this.directory = directory;
        this.executor = executor;
    }

    @Override
    public void run() {
        var futures = new LinkedList<Future<?>>();
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                futures.add(executor.submit(new DirectoryProcessor(file, executor)));
            } else {
                if (Utils.getFileExtension(file).equalsIgnoreCase("java"))
                    futures.add(executor.submit(new FileProcessor(file)));
            }
        }

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                // nothing
            }
        });

    }

}
