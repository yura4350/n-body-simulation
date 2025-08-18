/**
 * Tests the calcDistance method.
 */
public class TestCalcDistance {

    public static void main(String[] args) {
        checkCalcDistance();
    }

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkCalcDistance() {
        System.out.println("Checking calcDistance...");

        CelestialBody p1 = new CelestialBody(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        CelestialBody p2 = new CelestialBody(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        CelestialBody p3 = new CelestialBody(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcDistance(p2), 1.0, "calcDistance()", 0.01);
        checkEquals(p1.calcDistance(p3), 5.0, "calcDistance()", 0.01);
    }
}