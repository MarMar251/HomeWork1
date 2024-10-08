package org.mtexample;

import java.util.ArrayList;
import java.util.List;

public class EratosthenesSieve implements PrimeAlgo {
    @Override
    public List<Integer> getPrimes(int start, int end) {
        boolean[] isPrime = new boolean[end + 1];
        for (int i = 2; i <= end; i++) {
            isPrime[i] = true;
        }

        for (int p = 2; p * p <= end; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= end; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(start, 2); i <= end; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}