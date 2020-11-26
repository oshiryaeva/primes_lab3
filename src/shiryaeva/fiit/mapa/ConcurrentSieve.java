package shiryaeva.fiit.mapa;

import java.util.*;

/**
 * Parallel program for finding prime numbers from 0 to N.
 */
public class ConcurrentSieve {

    private static final int THREADS_NUM = Runtime.getRuntime().availableProcessors();
    private static final int THREADS_MAX = 64;
    private static final int RANGE_LIMIT = 10000;
    private static final List<Integer> primes = new LinkedList<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("6138 Shiryaeva Olga");
        System.out.println("Lab 3: Concurrent Sieve");
        long startTime = System.currentTimeMillis();
        System.out.println("Start time: " + (System.currentTimeMillis() - startTime));
        ConcurrentSieve sieve = new ConcurrentSieve();
        sieve.sieveAll();
        System.out.println("Primes from 2 to " + RANGE_LIMIT + ": ");
        System.out.println(primes.toString());
        System.out.println("Primes total: " + primes.size());
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }

    /**
     * Sieve all numbers to find primes in multiple threads.
     */
    public void sieveAll() {

        int threadsNum = Math.max(THREADS_MAX, THREADS_NUM);
        int sliceSize = RANGE_LIMIT / threadsNum;
        for (int i = 0; i <= RANGE_LIMIT; i += sliceSize) {
            int to = i + sliceSize;
            if (to > RANGE_LIMIT)
                to = RANGE_LIMIT;
            PrimeFinder finder = new PrimeFinder(i, to);
            finder.start();
            try {
                finder.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread " + finder.getName() + " join failed");

            }
        }
    }

    /**
     * Add prime to shared list.
     *
     * @param number the number
     */
    public static void addPrime(int number) {
        primes.add(number);
    }

    /**
     * Gets all primes from shared list.
     *
     * @return the primes
     */
    public static List<Integer> getPrimes() {
        return primes;
    }
}
