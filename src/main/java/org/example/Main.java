package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(printer::print).start();

        Producer producer1 = new Producer(printer, 3, 3);
        Producer producer2 = new Producer(printer, 5, 5);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(producer1);
        executor.submit(producer2);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdownNow();
    }
}
