/**
 * Tests the CelestialBody update method.
 */
public class TestUpdate {

    public static void main(String[] args) {
        checkUpdate();
    }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkUpdate() {
        System.out.println("Checking update...");

        CelestialBody body = new CelestialBody(1.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");
        body.update(2.0, 1.0, -0.5);

        checkEquals(7.8, body.getX(), "x position after update", 0.01);
        checkEquals(9.6, body.getY(), "y position after update", 0.01);
        checkEquals(3.4, body.getXVel(), "x velocity after update", 0.01);
        checkEquals(3.8, body.getYVel(), "y velocity after update", 0.01);
    }
}