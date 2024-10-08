package org.mtexample;

import java.util.ArrayList;
import java.util.List;

public class CalculatePrime {
    private final PrimeAlgo primeAlgo;

    public CalculatePrime(PrimeAlgo primeAlgo) {
        this.primeAlgo = primeAlgo;
    }

    public List<Integer> findPrimesInRange(int start, int end, int numThreads) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        // Create a list to store results from each thread
        List<List<Integer>> results = new ArrayList<>(numThreads);

        // Calculate the size of each range segment for threads
        int rangeSize = (end - start + 1) / numThreads;

        // Initialize the results list
        for (int i = 0; i < numThreads; i++) {
            results.add(new ArrayList<>());
        }

        // Start threads to find primes in segments
        for (int i = 0; i < numThreads; i++) {
            final int threadStart = start + i * rangeSize;
            final int threadEnd = (i == numThreads - 1) ? end : threadStart + rangeSize - 1;

            int finalI = i;
            Thread thread = new Thread(() -> {
                List<Integer> primes = primeAlgo.getPrimes(threadStart, threadEnd);
                results.set(finalI, primes);
            });

            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Collect all results into a single list
        List<Integer> allPrimes = new ArrayList<>();
        for (List<Integer> primes : results) {
            allPrimes.addAll(primes);
        }

        return allPrimes;
    }
}
