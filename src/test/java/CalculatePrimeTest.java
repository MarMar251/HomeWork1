import junit.framework.TestCase;
import org.mtexample.*;

import java.util.List;

public class CalculatePrimeTest extends TestCase {

    public void testCalculatePrimeWithExtremeRangesAndThreads() throws Exception {
        int[] ranges = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
        int[] threadCounts = {2, 4, 8, 16, 32, 64, 128, 256};
        int[] expectedPrimeCounts = {4, 25, 168, 1229, 9592, 78498, 664579};

        PrimeAlgo[] algorithms = {new AtkinSieve(), new EratosthenesSieve(), new TraditionalPrime()};

        for (PrimeAlgo algorithm : algorithms) {
            for (int i = 0; i < ranges.length; i++) {
                int end = ranges[i];
                int expectedCount = expectedPrimeCounts[i];
                for (int threads : threadCounts) {
                    CalculatePrime calculatePrime = new CalculatePrime(algorithm);

                    long startTime = System.nanoTime();
                    List<Integer> primes = calculatePrime.findPrimesInRange(1, end, threads);
                    long endTime = System.nanoTime();

                    assertNotNull("Primes list should not be null", primes);
                    assertFalse("Primes list should not be empty", primes.isEmpty());

                    int actualCount = primes.size();
                    assertEquals("Number of primes found should match expected count", expectedCount, actualCount);

                    System.out.printf("Algorithm: %s, Range: 1 to %d, Threads: %d, Time taken: %d ms, Primes found: %d%n",
                            algorithm.getClass().getSimpleName(), end, threads, (endTime - startTime) / 1_000_000, actualCount);
                }
            }
        }
    }
}