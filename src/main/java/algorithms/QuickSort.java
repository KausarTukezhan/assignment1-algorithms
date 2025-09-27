package algorithms;

public class QuickSort {

    // 🔹 Старая версия (без метрик)
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 Новая версия (с метриками)
    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length < 2) return;

        metrics.startTimer();
        quicksort(arr, 0, arr.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void quicksort(int[] arr, int low, int high, Metrics metrics) {
        if (low < high) {
            metrics.enterRecursion();

            int pivotIndex = partition(arr, low, high, metrics);

            quicksort(arr, low, pivotIndex - 1, metrics);
            quicksort(arr, pivotIndex + 1, high, metrics);

            metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int low, int high, Metrics metrics) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }
}
