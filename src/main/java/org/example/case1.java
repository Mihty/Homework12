package org.example;

public class case1 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[50];

        for (int i = 0; i < 50; i++) {
            final int threadNumber = i;
            threads[i] = new Thread(() -> {
                try {
                    if (threadNumber < 49) {
                        threads[threadNumber + 1].join();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Hello from thread " + threadNumber);
            });
        }

        for (int i = 49; i >= 0; i--) {
            threads[i].start();
        }
    }
}
