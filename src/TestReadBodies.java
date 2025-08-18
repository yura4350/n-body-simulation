import java.io.FileNotFoundException;

/**
 * Tests the NBody.readBodies method.
 */
public class TestReadBodies {

    private static boolean doubleEquals(double actual, double expected, double eps) {
        return Math.abs(expected - actual) <= eps * Math.max(expected, actual);
    }

    private static String checkReadBodies() throws FileNotFoundException {
        System.out.println("Checking readBodies...");
        
        CelestialBody[] bodies = NBody.readBodies("./data/planets.txt");

        if (bodies == null) {
            return "FAIL: readBodies() returned null";
        }
        if (bodies.length != 5) {
            return "FAIL: readBodies().length: Expected 5 and you gave " + bodies.length;
        }

        boolean foundEarth = false, foundMars = false, foundMercury = false;
        boolean foundSun = false, foundVenus = false;
        boolean dataCorrect = true;
        
        for (CelestialBody body : bodies) {
            String name = body.getName();
            if ("earth.gif".equals(name)) {
                foundEarth = true;
                if (!doubleEquals(body.getX(), 1.4960e+11, 0.01)) {
                    System.out.println("Earth doesn't have the correct x position!");
                    dataCorrect = false;
                }
            } else if ("mars.gif".equals(name)) {
                foundMars = true;
            } else if ("mercury.gif".equals(name)) {
                foundMercury = true;
                if (!doubleEquals(body.getY(), 0, 0.01)) {
                    System.out.println("Mercury doesn't have the correct y position!");
                    dataCorrect = false;
                }
            } else if ("sun.gif".equals(name)) {
                foundSun = true;
            } else if ("venus.gif".equals(name)) {
                foundVenus = true;
                if (!doubleEquals(body.getMass(), 4.8690e+24, 0.01)) {
                    System.out.println("Venus doesn't have the correct mass!");
                    dataCorrect = false;
                }
            } else {
                System.out.printf("Unexpected planet file: '%s'\n", name);
            }
        }

        String missingBodies = "";
        if (!foundEarth) missingBodies += "Earth, ";
        if (!foundMars) missingBodies += "Mars, ";
        if (!foundMercury) missingBodies += "Mercury, ";
        if (!foundSun) missingBodies += "Sun, ";
        if (!foundVenus) missingBodies += "Venus, ";
        
        if (missingBodies.length() > 0) {
            return "FAIL: readBodies() missing: " + missingBodies.substring(0, missingBodies.length() - 2);
        }
        if (!dataCorrect) {
            return "FAIL: readBodies() - some bodies have incorrect data!";
        }
        
        return "PASS: readBodies() - all tests passed!";
    }

    public static void main(String[] args) throws FileNotFoundException {
        String result = checkReadBodies();
        System.out.println(result);
    }
}