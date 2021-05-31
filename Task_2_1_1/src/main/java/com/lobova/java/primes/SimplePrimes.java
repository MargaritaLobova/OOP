package com.lobova.java.primes;

/**
 * Sequential run of prime check.
 */
public class SimplePrimes {
    static boolean hasNotPrime = false;

    /**
     * @param array of numbers that are needed to be checked
     * @return true if there is not prime number in the array(at least one), false if there are
     * some prime numbers.
     */
    public static boolean sequentialRun(final long[] array) {
        for (long l : array) {
            if (IsPrime.isNotPrime(l)) {
                hasNotPrime = true;
                break;
            }
        }
        return hasNotPrime;
    }
}
