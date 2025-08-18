import java.io.FileNotFoundException;

/**
 * Tests the NBody.readRadius method.
 */
public class TestReadRadius {

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkReadRadius() throws FileNotFoundException {
        System.out.println("Checking readRadius...");
        
        double actualOutput = NBody.readRadius("./data/planets.txt");
        checkEquals(actualOutput, 2.50E11, "readRadius() with planets.txt", 0.01);
        
        actualOutput = NBody.readRadius("./data/binaryStars.txt");
        checkEquals(actualOutput, 3.00E11, "readRadius() with binaryStars.txt", 0.01);
    }

    public static void main(String[] args) throws FileNotFoundException {
        checkReadRadius();
    }
}