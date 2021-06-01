package com.lobova.java.primes;

/**
 * Quick prime number check.
 */
public class IsPrime {
    /**
     * @param n - number to check for primality
     * @return true if it is prime, false if not
     */
    private static boolean primeCheck(final long n) {
        if (n == 2 || n == 3 || n == 5) {
            return true;
        }
        if (n <= 1 || (n & 1) == 0) {
            return false;
        }
        for (int i = 3; (long) i * i <= n; i += 2)
            if (n % i == 0) {
            return false;
        }
        return true;
    }

    /**
     * @param n - number to check for primality
     * @return true if it is NOT prime, false if is
     */
    public static boolean isNotPrime(final long n) {
        return !primeCheck(n);
    }
}
