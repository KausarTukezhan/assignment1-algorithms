package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testSimpleArray() {
        int[] arr = {5, 2, 9, 1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 9}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {3, 3, 3};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{3, 3, 3}, arr);
    }
}
