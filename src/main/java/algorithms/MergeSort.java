package algorithms;

public class MergeSort {

    // Порог: если подмассив маленький, используем сортировку вставками
    private static final int CUTOFF = 10;

    // 🔹 Старая версия (без метрик)
    public static void sort(int[] arr) {
        if (arr == null) return;
        if (arr.length < 2) return;

        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);

        merge(arr, buffer, left, mid, right);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
    }

    // 🔹 Новая версия (с метриками)
    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null) return;
        if (arr.length < 2) return;

        metrics.startTimer();
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        if (left >= right) return;

        metrics.enterRecursion();

        int mid = left + (right - left) / 2;

        mergeSort(arr, buffer, left, mid, metrics);
        mergeSort(arr, buffer, mid + 1, right, metrics);

        merge(arr, buffer, left, mid, right, metrics);

        metrics.exitRecursion();
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
    }
}