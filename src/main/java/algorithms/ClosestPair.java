package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    // Класс точки
    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // 🔹 Старая версия (без метрик)
    public static double findClosestPair(Point[] points) {
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));
        return closestPair(pointsSortedByX, 0, points.length - 1);
    }

    private static double closestPair(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        double leftMin = closestPair(points, left, mid);
        double rightMin = closestPair(points, mid + 1, right);

        double d = Math.min(leftMin, rightMin);

        return Math.min(d, stripClosest(points, left, right, mid, d));
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        }
        return minDist;
    }

    private static double stripClosest(Point[] points, int left, int right, int mid, double d) {
        Point midPoint = points[mid];
        Point[] strip = Arrays.stream(points, left, right + 1)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        double minDist = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }

    private static double distance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

    // 🔹 Новая версия (с метриками)
    public static double findClosestPair(Point[] points, Metrics metrics) {
        metrics.startTimer();
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));
        double result = closestPair(pointsSortedByX, 0, points.length - 1, metrics);
        metrics.stopTimer();
        return result;
    }

    private static double closestPair(Point[] points, int left, int right, Metrics metrics) {
        if (right - left <= 3) {
            return bruteForce(points, left, right, metrics);
        }

        int mid = (left + right) / 2;
        double leftMin = closestPair(points, left, mid, metrics);
        double rightMin = closestPair(points, mid + 1, right, metrics);

        double d = Math.min(leftMin, rightMin);

        return Math.min(d, stripClosest(points, left, right, mid, d, metrics));
    }

    private static double bruteForce(Point[] points, int left, int right, Metrics metrics) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        }
        return minDist;
    }

    private static double stripClosest(Point[] points, int left, int right, int mid, double d, Metrics metrics) {
        Point midPoint = points[mid];
        Point[] strip = Arrays.stream(points, left, right + 1)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        double minDist = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < minDist; j++) {
                metrics.incrementComparisons();
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }
}
