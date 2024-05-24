package org.example;

public class case2 {
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numbers = {29, 15, 7, 11, 2, 18, 25, 17, 19, 23, 13, 6, 4, 31, 37, 41, 43, 47, 53, 59};
        final int[] primeCount = {0};

        Thread thread1 = new Thread(() -> {
            int count = 0;
            for (int i = 0; i < numbers.length / 2; i++) {
                if (isPrime(numbers[i])) {
                    count++;
                }
            }
            synchronized (primeCount) {
                primeCount[0] += count;
                System.out.println("Thread 1 found " + count + " prime numbers.");
            }
        });

        Thread thread2 = new Thread(() -> {
            int count = 0;
            for (int i = numbers.length / 2; i < numbers.length; i++) {
                if (isPrime(numbers[i])) {
                    count++;
                }
            }
            synchronized (primeCount) {
                primeCount[0] += count;
                System.out.println("Thread 2 found " + count + " prime numbers.");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Total prime numbers: " + primeCount[0]);
    }
}
