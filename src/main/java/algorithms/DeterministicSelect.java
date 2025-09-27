package algorithms;

import java.util.Arrays;

public class DeterministicSelect {

    // 🔹 Старая версия (без метрик)
    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return select(arr, left, pivotIndex - 1, k);
        } else {
            return select(arr, pivotIndex + 1, right, k);
        }
    }

    // 🔹 Новая версия (с метриками)
    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        metrics.startTimer();
        int result = select(arr, 0, arr.length - 1, k, metrics);
        metrics.stopTimer();
        return result;
    }

    private static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        if (left == right) {
            return arr[left];
        }

        metrics.enterRecursion();

        int pivotIndex = medianOfMedians(arr, left, right, metrics);
        pivotIndex = partition(arr, left, right, pivotIndex, metrics);

        int result;
        if (k == pivotIndex) {
            result = arr[k];
        } else if (k < pivotIndex) {
            result = select(arr, left, pivotIndex - 1, k, metrics);
        } else {
            result = select(arr, pivotIndex + 1, right, k, metrics);
        }

        metrics.exitRecursion();
        return result;
    }

    // 🔹 Median of Medians
    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            int median = subLeft + (subRight - subLeft) / 2;

            swap(arr, left + i, median);
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            int median = subLeft + (subRight - subLeft) / 2;

            swap(arr, left + i, median);
        }

        return medianOfMedians(arr, left, left + numMedians - 1, metrics);
    }

    // 🔹 Partition
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right);
        return storeIndex;
    }

    // 🔹 Swap
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}