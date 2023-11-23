package org.example;

import java.util.Random;

public class Producer implements Runnable {
    private final Random random;
    private final Printer printer;
    private int initialTerm;
    private final int commonDifference;
    private final Object lock = new Object();

    public Producer(Printer printer, int initialTerm, int commonDifference) {
        this.printer = printer;
        this.initialTerm = initialTerm;
        this.commonDifference = commonDifference;
        this.random = new Random();
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int value = calculateNextTerm();
                printer.addToQueue(value);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calculateNextTerm() {
        synchronized (lock) {
            int nextTerm = initialTerm;
            initialTerm += commonDifference;
            return nextTerm;
        }
    }
}
