package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    void testSelectMedian() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int median = DeterministicSelect.select(arr.clone(), arr.length / 2);
        Arrays.sort(arr);
        assertEquals(arr[arr.length / 2], median);
    }

    @Test
    void testSelectMin() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int min = DeterministicSelect.select(arr.clone(), 0);
        assertEquals(3, min);
    }

    @Test
    void testSelectMax() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int max = DeterministicSelect.select(arr.clone(), arr.length - 1);
        assertEquals(20, max);
    }

    @Test
    void testSelectRandomK() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3};
        int k = 3;
        int kth = DeterministicSelect.select(arr.clone(), k);
        Arrays.sort(arr);
        assertEquals(arr[k], kth);
    }
}
