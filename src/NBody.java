import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main driver for N-body gravitational simulation.
 * Reads simulation data, manages the physics loop, and handles visualization.
 * 
 * @author Iurii Beliaev
 */
public class NBody {
    
    /**
     * Reads the universe radius from a simulation data file.
     * 
     * @param filename path to the data file
     * @return radius of the universe for scaling
     * @throws FileNotFoundException if file cannot be opened
     */
    public static double readRadius(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        
        scanner.nextInt(); // Skip number of bodies
        double radius = scanner.nextDouble();
        scanner.close();
        
        return radius;
    }
    
    /**
     * Reads celestial body data from file and creates an array of CelestialBody objects.
     * 
     * @param filename path to the data file
     * @return array of CelestialBody objects
     * @throws FileNotFoundException if file cannot be opened
     */
    public static CelestialBody[] readBodies(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        
        int numBodies = scanner.nextInt();
        scanner.nextDouble(); // Skip radius
        
        CelestialBody[] bodies = new CelestialBody[numBodies];
        
        for (int i = 0; i < numBodies; i++) {
            double xPos = scanner.nextDouble();
            double yPos = scanner.nextDouble();
            double xVel = scanner.nextDouble();
            double yVel = scanner.nextDouble();
            double mass = scanner.nextDouble();
            String imageFile = scanner.next();
            
            bodies[i] = new CelestialBody(xPos, yPos, xVel, yVel, mass, imageFile);
        }
        
        scanner.close();
        return bodies;
    }
    
    /**
     * Main simulation loop. Reads data, runs physics simulation, and handles graphics.
     * 
     * @param args command line arguments: [totalTime] [deltaTime] [dataFile]
     * @throws FileNotFoundException if data file cannot be opened
     */
    public static void main(String[] args) throws FileNotFoundException {
        double totalTime = 39447000.0;
        double dt = 25000.0;
        String dataFile = "./data/planets.txt";
        
        if (args.length > 2) {
            totalTime = Double.parseDouble(args[0]);
            dt = Double.parseDouble(args[1]);
            dataFile = args[2];
        }
        
        CelestialBody[] bodies = readBodies(dataFile);
        double radius = readRadius(dataFile);
        
        // Initialize graphics
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        
        // Force arrays for efficient computation
        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];
        
        // Main simulation loop
        for (double t = 0.0; t < totalTime; t += dt) {
            // Calculate net forces for all bodies
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            
            // Update all body positions and velocities
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            
            // Render current frame
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            for (CelestialBody body : bodies) {
                body.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(10);
        }
        
        // Output final simulation state
        System.out.printf("%d\n", bodies.length);
        System.out.printf("%.2e\n", radius);
        for (CelestialBody body : bodies) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            body.getX(), body.getY(), body.getXVel(), body.getYVel(), 
                            body.getMass(), body.getName());
        }
    }
}