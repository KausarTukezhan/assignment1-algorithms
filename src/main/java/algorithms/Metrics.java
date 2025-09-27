package algorithms;

public class Metrics {
    private long comparisons = 0;
    private int recursionDepth = 0;
    private int maxRecursionDepth = 0;
    private long startTime;
    private long endTime;

    public void incrementComparisons() {
        comparisons++;
    }

    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public double getTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    @Override
    public String toString() {
        return String.format(
                "Time: %.3f ms, Comparisons: %d, Max Recursion Depth: %d",
                getTimeMillis(), comparisons, maxRecursionDepth
        );
    }
}