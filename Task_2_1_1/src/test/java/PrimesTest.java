import com.lobova.java.primes.ParallelStreamPrimes;
import com.lobova.java.primes.SimplePrimes;
import com.lobova.java.primes.ThreadPrime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PrimesTest {

    private final long[] testArray = {24571L, 33391L, 35317L, 27644437L, 877L, 115249L, 76801L,
            2029L, 10267L, 13669L, 12097L, 631L, 33391L, 2124679L, 16769023L};
    private long[] enlargedTestArray = new long[100000];

    public void arrayIncreasing() {
        for (int i = 0; i < enlargedTestArray.length; i++) {
            enlargedTestArray[i] = testArray[i % testArray.length];
        }
    }

    @Test
    public void simpleTest() {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        assertFalse(SimplePrimes.sequentialRun(enlargedTestArray));
        System.out.println("1");
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    @Test
    public void threadTest2() throws Exception {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        assertFalse(ThreadPrime.threadRun(enlargedTestArray, 2));
        System.out.println("2");
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    @Test
    public void threadTest4() throws Exception {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        assertFalse(ThreadPrime.threadRun(enlargedTestArray, 4));
        System.out.println("3");
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    @Test
    public void threadTest8() throws Exception {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        assertFalse(ThreadPrime.threadRun(enlargedTestArray, 8));
        System.out.println("4");
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    @Test
    public void threadTest16() throws Exception {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        assertFalse(ThreadPrime.threadRun(enlargedTestArray, 16));
        System.out.println("5");
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    @Test
    public void streamTest() {
        long m = System.currentTimeMillis();
        arrayIncreasing();
        int index = 0;
        Long[] testArrayLong = new Long[enlargedTestArray.length];
        for (final Long value : enlargedTestArray) {
            testArrayLong[index++] = value;
        }
        assertFalse(ParallelStreamPrimes.streamRun(testArrayLong));
        System.out.println("6");
        System.out.println((double) (System.currentTimeMillis() - m));
    }
}
