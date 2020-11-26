package shiryaeva.fiit.mapa;

import java.util.LinkedList;
import java.util.List;

/**
 * Sequential program for finding prime numbers from 0 to N.
 */
public class SequentialSieve {
    private static final int RANGE_LIMIT = 1000000;
    public static final List<Integer> primes = new LinkedList<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("6138 Shiryaeva Olga");
        System.out.println("Lab 3: Sequential Sieve");
        long startTime = System.currentTimeMillis();
        SequentialSieve sequentialSieve = new SequentialSieve();
        sequentialSieve.sieve();
        System.out.println("Primes from 2 to  " + RANGE_LIMIT + ": ");
        System.out.println(primes.toString());
        System.out.println("Primes total: " + primes.size());
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }

    /**
     * Sieve all numbers to find primes in one thread.
     */
    private void sieve() {
        for (int i = 0; i <= RANGE_LIMIT; i++) {
            if (isPrime(i) && i != 0 && i != 1) {
                primes.add(i);
            }
        }
    }

    /**
     * Check if a given number is prime.
     */
    private boolean isPrime(int number) {
        for (int prime : primes) {
            if (Math.sqrt(prime) < number && number % prime == 0) {
                return false;
            }
        }
        return true;
    }
}
