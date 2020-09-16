import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {
    HeapSort heapSort = new HeapSort();
    @Test
    void empty_test() {
        int ar[] = {};
        int expected[] = {};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected,actual);
    }
    @Test
    void standard_test(){
        int ar[] = {1, 2, 6, 7, 10, 363, 12, 1000, 5, 3};
        int expected[] ={1, 2, 3, 5, 6, 7, 10, 12, 363, 1000};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected, actual);
    }
    @Test
    void duplicates_test() {
        int ar[] = {1, 2, 3, 3, 5, 2, 1};
        int expected[] = {1, 1, 2, 2, 3, 3, 5};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected,actual);
    }
    @Test
    void alreadySorted_test() {
        int ar[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected,actual);
    }
    @Test
    void inverted_test() {
        int ar[] = {5, 4, 3, 2, 1, 0};
        int expected[] = {0, 1, 2, 3, 4, 5};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected,actual);
    }
    @Test
    void negative_test() {
        int ar[] = {-1, -2, -7, -8, -4};
        int expected[] = {-8, -7, -4, -2, -1};
        int actual[] = heapSort.sort(ar);
        assertArrayEquals(expected,actual);
    }
}