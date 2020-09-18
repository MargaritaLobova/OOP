import com.oop.HeapSort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    @Test
    void emptyTest() {
        int[] ar = {};
        int[] expected = {};
        HeapSort.sort(ar);
        assertArrayEquals(expected, ar);
    }

    @Test
    void standardTest(){
        int[] ar = {1, 2, 6, 7, 10, 363, 12, 1000, 5, 3};
        int[] expected ={1, 2, 3, 5, 6, 7, 10, 12, 363, 1000};
        HeapSort.sort(ar);
        assertArrayEquals(expected, ar);
    }

    @Test
    void duplicatesTest() {
        int ar[] = {1, 2, 3, 3, 5, 2, 1};
        int expected[] = {1, 1, 2, 2, 3, 3, 5};
        HeapSort.sort(ar);
        assertArrayEquals(expected,ar);
    }

    @Test
    void alreadySortedTest() {
        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        HeapSort.sort(ar);
        assertArrayEquals(expected,ar);
    }

    @Test
    void invertedTest() {
        int[] ar = {5, 4, 3, 2, 1, 0};
        int[] expected = {0, 1, 2, 3, 4, 5};
        HeapSort.sort(ar);
        assertArrayEquals(expected,ar);
    }

    @Test
    void negativeTest() {
        int[] ar = {-1, -2, -7, -8, -4};
        int[] expected = {-8, -7, -4, -2, -1};
        HeapSort.sort(ar);
        assertArrayEquals(expected,ar);
    }

    @Test
    void generatedArrayTest(){
        int[] ar = new int[10000];
        int[] generated = new int[10000];
        Random random = new Random();
        for(int i=0; i<10000; i++){
            generated[i] = random.nextInt(10000);
            ar[i] = generated[i];
        }
        Arrays.sort(ar);
        HeapSort.sort(generated);
        assertArrayEquals(generated, ar);
    }
}