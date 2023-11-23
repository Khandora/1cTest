package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Printer {
    private final BlockingQueue<Integer> queue;

    public Printer() {
        queue = new LinkedBlockingQueue<>();
    }

    public void print() {
        try {
            while (true) {
                Integer value = queue.take();
                System.out.println("Printing: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void addToQueue(int value) {
        queue.add(value);
    }
}
