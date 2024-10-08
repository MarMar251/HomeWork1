package org.mtexample;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display menu options
            System.out.println("Choose a prime number algorithm:");
            System.out.println("1. Traditional Prime Checker ");
            System.out.println("2. Eratosthenes Sieve");
            System.out.println("3. Atkin Sieve");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            PrimeAlgo sieve = null;

            switch (choice) {
                case 1:
                    sieve = new TraditionalPrime();
                    break;
                case 2:
                    sieve = new EratosthenesSieve();
                    break;
                case 3:
                    sieve = new AtkinSieve();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    continue; // Exit the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Prompt again for valid input
            }

            // Get user input for start, end, and number of threads
            System.out.print("Enter start of range: ");
            int startRange = scanner.nextInt();
            System.out.print("Enter end of range: ");
            int endRange = scanner.nextInt();
            System.out.print("Enter number of threads to use: ");
            int numThreads = scanner.nextInt();

            CalculatePrime calculatePrime = new CalculatePrime(sieve);

            try {
                List<Integer> primesFound = calculatePrime.findPrimesInRange(startRange, endRange, numThreads);
                System.out.println("Primes found: " + primesFound);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (choice != 0);

        scanner.close(); // Close the scanner resource

    }
}