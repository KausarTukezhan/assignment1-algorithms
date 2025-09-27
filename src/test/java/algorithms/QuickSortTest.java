package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testSimpleArray() {
        int[] arr = {5, 2, 9, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 9}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {4, 3, 2, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {5, 3, 5, 3, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 3, 5, 5}, arr);
    }
}