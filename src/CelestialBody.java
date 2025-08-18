/**
 * Represents a celestial body in an N-body gravitational simulation.
 * Models position, velocity, mass, and gravitational interactions.
 * 
 * @author Iurii Beliaev
 */
public class CelestialBody {
    private static final double G = 6.67e-11; // Gravitational constant

    private double myXPos;
    private double myYPos;
    private double myXVel;
    private double myYVel;
    private double myMass;
    private String myFileName;

    /**
     * Creates a new celestial body with specified initial conditions.
     * 
     * @param xp initial x position
     * @param yp initial y position
     * @param xv initial x velocity
     * @param yv initial y velocity
     * @param mass mass of the body
     * @param filename image file for visualization
     */
    public CelestialBody(double xp, double yp, double xv, double yv, double mass, String filename) {
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    public double getX() {
        return myXPos;
    }

    public double getY() {
        return myYPos;
    }

    public double getXVel() {
        return myXVel;
    }

    public double getYVel() {
        return myYVel;
    }

    public double getMass() {
        return myMass;
    }

    public String getName() {
        return myFileName;
    }

    /**
     * Calculates the Euclidean distance between this body and another.
     * 
     * @param b the other celestial body
     * @return distance between the two bodies
     */
    public double calcDistance(CelestialBody b) {
        double dx = myXPos - b.getX();
        double dy = myYPos - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates the gravitational force exerted by another body on this body.
     * 
     * @param b the body exerting the force
     * @return magnitude of gravitational force
     */
    public double calcForceExertedBy(CelestialBody b) {
        double distance = calcDistance(b);
        return G * myMass * b.getMass() / (distance * distance);
    }

    /**
     * Calculates the x-component of gravitational force from another body.
     * 
     * @param b the body exerting the force
     * @return x-component of force
     */
    public double calcForceExertedByX(CelestialBody b) {
        double dx = b.getX() - myXPos;
        double distance = calcDistance(b);
        return calcForceExertedBy(b) * dx / distance;
    }

    /**
     * Calculates the y-component of gravitational force from another body.
     * 
     * @param b the body exerting the force
     * @return y-component of force
     */
    public double calcForceExertedByY(CelestialBody b) {
        double dy = b.getY() - myYPos;
        double distance = calcDistance(b);
        return calcForceExertedBy(b) * dy / distance;
    }

    /**
     * Calculates the net x-component force from all bodies in the array.
     * 
     * @param bodies array of all celestial bodies
     * @return net x-component force
     */
    public double calcNetForceExertedByX(CelestialBody[] bodies) {
        double netForce = 0.0;
        for (CelestialBody body : bodies) {
            if (!body.equals(this)) {
                netForce += calcForceExertedByX(body);
            }
        }
        return netForce;
    }

    /**
     * Calculates the net y-component force from all bodies in the array.
     * 
     * @param bodies array of all celestial bodies
     * @return net y-component force
     */
    public double calcNetForceExertedByY(CelestialBody[] bodies) {
        double netForce = 0.0;
        for (CelestialBody body : bodies) {
            if (!body.equals(this)) {
                netForce += calcForceExertedByY(body);
            }
        }
        return netForce;
    }

    /**
     * Updates position and velocity based on applied forces using Euler integration.
     * 
     * @param deltaT time step for integration
     * @param xforce net force in x-direction
     * @param yforce net force in y-direction
     */
    public void update(double deltaT, double xforce, double yforce) {
        double ax = xforce / myMass;
        double ay = yforce / myMass;

        double nvx = myXVel + deltaT * ax;
        double nvy = myYVel + deltaT * ay;

        double nx = myXPos + deltaT * nvx;
        double ny = myYPos + deltaT * nvy;

        myXPos = nx;
        myYPos = ny;
        myXVel = nvx;
        myYVel = nvy;
    }

    /**
     * Renders this celestial body at its current position.
     */
    public void draw() {
        StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
    }
}