/**
 * Tests the calcForceExertedBy method.
 */
public class TestCalcForceExertedBy {

    public static void main(String[] args) {
        checkCalcForceExertedBy();
    }

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkCalcForceExertedBy() {
        System.out.println("Checking calcForceExertedBy...");

        CelestialBody p1 = new CelestialBody(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        CelestialBody p2 = new CelestialBody(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        CelestialBody p3 = new CelestialBody(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcForceExertedBy(p2), 133.4, "calcForceExertedBy()", 0.01);
        checkEquals(p1.calcForceExertedBy(p3), 6.67e-11, "calcForceExertedBy()", 0.01);
    }
}