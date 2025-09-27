package algorithms;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        // ===== Тест 1: MergeSort =====
        int[] arr1 = random.ints(20, 0, 100).toArray();
        Metrics m1 = new Metrics();
        MergeSort.sort(arr1.clone(), m1);
        System.out.println("MergeSort: " + m1);

        // ===== Тест 2: QuickSort =====
        int[] arr2 = random.ints(20, 0, 100).toArray();
        Metrics m2 = new Metrics();
        QuickSort.sort(arr2.clone(), m2);
        System.out.println("QuickSort: " + m2);

        // ===== Тест 3: Deterministic Select =====
        int[] arr3 = random.ints(20, 0, 100).toArray();
        Metrics m3 = new Metrics();
        int k = arr3.length / 2; // ищем медиану
        int median = DeterministicSelect.select(arr3.clone(), k, m3);
        System.out.println("DeterministicSelect (median = " + median + "): " + m3);

        // ===== Тест 4: Closest Pair =====
        ClosestPair.Point[] points = new ClosestPair.Point[10];
        for (int i = 0; i < points.length; i++) {
            points[i] = new ClosestPair.Point(random.nextDouble() * 100, random.nextDouble() * 100);
        }
        Metrics m4 = new Metrics();
        double minDist = ClosestPair.findClosestPair(points, m4);
        System.out.println("ClosestPair (minDist = " + minDist + "): " + m4);
    }
}