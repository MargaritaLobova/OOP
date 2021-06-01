package com.lobova.java.primes;
import java.util.Arrays;

/**
 * Parallel check with usage of the Thread class with the ability
 * to specify the number of threads used.
 */
public class ThreadPrime {
    public static int threads = Runtime.getRuntime().availableProcessors();
    private static boolean hasNotPrime = false;
    private static long[] arr;

    /**
     * @param array           of numbers that are needed to be checked.
     * @param numberOfThreads if its needed, otherwise - all available processors.
     * @return true if there is not prime number in the array(at least one), false if there are
     * some prime numbers.
     * @throws Exception
     */
    public static boolean threadRun(final long[] array, final int numberOfThreads) throws Exception {
        if (numberOfThreads > 0 && numberOfThreads < threads) {
            threads = numberOfThreads;
        }
        Thread[] t = new Thread[threads];
        arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < threads; i++) {
            t[i] = new Thread(new PrimeRun(i));
            t[i].start();
        }
        for (int i = 0; i < threads; i++) {
            t[i].join();
        }
        return hasNotPrime;
    }

    public static long[] getArray() {
        return arr;
    }

    public static synchronized  void setHasNotPrime() {
        hasNotPrime = true;
    }
}

/**
 * Class to work with threads.
 */
class PrimeRun implements Runnable {
    final long[] array = ThreadPrime.getArray();
    final int id;

    /**
     * @param i thread number
     */
    public PrimeRun(final int i) {
        id = i;
    }

    /**
     * Method to run thread check.
     */
    public void run() {
        for (long l : array) {
            if (l % ThreadPrime.threads == id && IsPrime.isNotPrime(l)) {
                ThreadPrime.setHasNotPrime();
                break;
            }
        }
    }
}
