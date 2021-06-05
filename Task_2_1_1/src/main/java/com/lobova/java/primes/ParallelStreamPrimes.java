package com.lobova.java.primes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Parallel check with usage of the ParallelStream.
 */
public class ParallelStreamPrimes {
    private static boolean hasNotPrime = false;

    /**
     * @param array of numbers that are needed to be checked.
     * @return true if there is not prime number in the array(at least one), false if there are
     * some prime numbers.
     */
    public static boolean streamRun(final Long[] array) {
        List<Long> list = Arrays.asList(array);
        Optional<Long> n = list
                .parallelStream()
                .filter(IsPrime::isNotPrime)
                .findFirst();
        if (n.isPresent()) {
            hasNotPrime = true;
        }
        return hasNotPrime;
    }
}
