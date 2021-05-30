package com.lobova.java.primes;
import java.util.Arrays;

/**
 * Parallel check with usage of the Thread class with the ability
 * to specify the number of threads used.
 */
public class ThreadPrime {
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static boolean hasNotPrime = false;
    public static long[] arr;

    /**
     * @param array           of numbers that are needed to be checked.
     * @param numberOfThreads if its needed, otherwise - all available processors.
     * @return true if there is not prime number in the array(at least one), false if there are
     * some prime numbers.
     * @throws Exception
     */
    public static boolean threadRun(long[] array, int numberOfThreads) throws Exception {
        if (numberOfThreads > 0 && numberOfThreads < THREADS)
            THREADS = numberOfThreads;
        Thread[] t = new Thread[THREADS];
        arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < THREADS; i++) {
            t[i] = new Thread(new PrimeRun(i));
            t[i].start();
        }
        for (int i = 0; i < THREADS; i++)
            t[i].join();
        return hasNotPrime;
    }

    public static long[] getArray() {
        return arr;
    }

    public synchronized static void setHasNotPrime() {
        hasNotPrime = true;
    }
}

class PrimeRun implements Runnable {
    final long[] array = ThreadPrime.getArray();
    final int ID;

    public PrimeRun(int i) {
        ID = i;
    }

    public void run() {
        for (long l : array) {
            if (l % ThreadPrime.THREADS == ID && isPrime.isNotPrime(l)) {
                ThreadPrime.setHasNotPrime();
                break;
            }
        }
    }
}
