package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSimplePoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };
        double result = ClosestPair.findClosestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4)
        };
        double result = ClosestPair.findClosestPair(points);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    void testCollinearPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 0),
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(3, 0)
        };
        double result = ClosestPair.findClosestPair(points);
        assertEquals(1.0, result, 1e-9);
    }
}
