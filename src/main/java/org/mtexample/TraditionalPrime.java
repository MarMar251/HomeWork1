package org.mtexample;

import java.util.ArrayList;
import java.util.List;

public class TraditionalPrime implements PrimeAlgo {
    @Override
    public List<Integer> getPrimes(int start, int end) {
        List<Integer> primes = new ArrayList<>();

        for (int number = Math.max(start, 2); number <= end; number++) {
            if (isPrime(number)) {
                primes.add(number);
            }
        }

        return primes;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}