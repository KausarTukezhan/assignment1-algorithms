package algorithms;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000}; // размеры массивов
        Random random = new Random();

        for (int n : sizes) {
            System.out.println("\n=== n = " + n + " ===");

            int[] arr1 = random.ints(n, 0, 10000).toArray();
            Metrics m1 = new Metrics();
            MergeSort.sort(arr1.clone(), m1);
            System.out.println("MergeSort: " + m1);

            int[] arr2 = random.ints(n, 0, 10000).toArray();
            Metrics m2 = new Metrics();
            QuickSort.sort(arr2.clone(), m2);
            System.out.println("QuickSort: " + m2);

            int[] arr3 = random.ints(n, 0, 10000).toArray();
            Metrics m3 = new Metrics();
            int k = n / 2;
            int median = DeterministicSelect.select(arr3.clone(), k, m3);
            System.out.println("DeterministicSelect (median = " + median + "): " + m3);

            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new ClosestPair.Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
            }
            Metrics m4 = new Metrics();
            double minDist = ClosestPair.findClosestPair(points, m4);
            System.out.println("ClosestPair (minDist = " + minDist + "): " + m4);
        }
    }
}