package org.mtexample;

import java.util.ArrayList;
import java.util.List;

public class AtkinSieve implements PrimeAlgo {
    @Override
    public List<Integer> getPrimes(int start, int end) {
        if (end < 2) {
            return new ArrayList<>();
        }

        boolean[] isPrime = new boolean[end + 1];
        int limit = Math.max(end, 2);
        int sqrLim = (int) Math.sqrt(limit);

        // Initialize the sieve
        for (int i = 0; i <= limit; i++) {
            isPrime[i] = false;
        }

        if (limit >= 2) isPrime[2] = true;
        if (limit >= 3) isPrime[3] = true;

        // Main algorithm
        for (int x = 1; x <= sqrLim; x++) {
            int x2 = x * x;
            for (int y = 1; y <= sqrLim; y++) {
                int y2 = y * y;

                // First condition
                int n = 4 * x2 + y2;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    isPrime[n] = !isPrime[n];
                }

                // Second condition
                n = 3 * x2 + y2;
                if (n <= limit && n % 12 == 7) {
                    isPrime[n] = !isPrime[n];
                }

                // Third condition
                n = 3 * x2 - y2;
                if (x > y && n <= limit && n % 12 == 11) {
                    isPrime[n] = !isPrime[n];
                }
            }
        }

        // Eliminate multiples of squares of primes
        for (int i = 5; i <= sqrLim; i++) {
            if (isPrime[i]) {
                int n = i * i;
                for (int j = n; j <= limit; j += n) {
                    isPrime[j] = false;
                }
            }
        }

        // Collect the primes into a list
        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(start, 2); i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
