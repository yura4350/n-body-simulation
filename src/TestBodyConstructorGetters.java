/**
 * Tests the CelestialBody constructor and getter methods.
 */
public class TestBodyConstructorGetters {

    public static void main(String[] args) {
        checkBodyConstructor();
    }

    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkStringEquals(String expected, String actual, String label) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkBodyConstructor() {
        System.out.println("Checking CelestialBody constructor and getters...");

        double xPos = 1.0, yPos = 2.0, xVel = 3.0, yVel = 4.0, mass = 5.0;
        String imgFileName = "jupiter.gif";

        CelestialBody body = new CelestialBody(xPos, yPos, xVel, yVel, mass, imgFileName);

        checkEquals(xPos, body.getX(), "x position");
        checkEquals(yPos, body.getY(), "y position");
        checkEquals(xVel, body.getXVel(), "x velocity");
        checkEquals(yVel, body.getYVel(), "y velocity");
        checkEquals(mass, body.getMass(), "mass");
        checkStringEquals(imgFileName, body.getName(), "image filename");
    }
}