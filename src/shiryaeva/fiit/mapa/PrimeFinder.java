package shiryaeva.fiit.mapa;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Finds all primes in a given range.
 * Runs in a separate thread.
 */
public class PrimeFinder extends Thread {

    private static int FROM = 0;
    private static int TO = 0;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock(false);

    /**
     * Instantiates a new Prime finder with a range of numbers.
     *
     * @param from the from
     * @param to   the to
     */
    public PrimeFinder(int from, int to) {
        FROM = from;
        TO = to;
    }

    @Override
    public synchronized void start() {
        super.start();
        primesInRange();
    }

    /**
     * Sieve all numbers in the thread range.
     */
    private void primesInRange() {
        while (FROM < TO) {
            boolean flag = false;
            lock.readLock().lock();
            for (int prime : ConcurrentSieve.getPrimes()) {
                if (Math.sqrt(prime) < FROM && FROM % prime == 0) {
                    flag = true;
                    break;
                }
            }
            lock.readLock().unlock();
            if (!flag && FROM != 0 && FROM != 1) {
                lock.writeLock().lock();
                ConcurrentSieve.addPrime(FROM);
                lock.writeLock().unlock();
            }
            ++FROM;
        }
    }
}
