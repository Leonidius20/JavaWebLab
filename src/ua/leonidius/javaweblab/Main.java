package ua.leonidius.javaweblab;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Знайти в заданій директорії всі файли, що містять вихідний код Java- програми,
 * і виконати у всіх лексемах, що мають довжину більше ніж три символи, заміну
 * символів на прописні (=великі). Назви усіх знайдених файлів вивести в консоль
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Path relativeCurrentPath = Paths.get("");
        File directory;

        // ExecutorService executor = Executors.newCachedThreadPool();

        ExecutorService executor = new ThreadPoolExecutor(
                5, 15, 1,
                TimeUnit.MILLISECONDS, new SynchronousQueue<>(), (ThreadFactory) Thread::new);

        do {
            directory = relativeCurrentPath.toAbsolutePath().toFile();
            System.out.println("Choose directory (leave empty for " + directory.getPath() + ")");
            String input = new Scanner(System.in).nextLine();

            if (!input.isEmpty())
                directory = new File(input);

        } while (!directory.exists());

        var future = executor.submit(new DirectoryProcessor(directory, executor));
        future.get();
        executor.shutdown();
    }

}

// new -> runnable -> running -> waiting/blocking/sleeping -> terminated
// regular and daemon (the latter ends if no regular threads are running)