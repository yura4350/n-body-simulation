

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 *
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Accessor for the y-velocity.
	 * @return value of this object's y-velocity
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
		
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double d = Math.sqrt((this.myXPos-b.getX())*(this.myXPos-b.getX())+(this.myYPos-b.getY())*(this.myYPos-b.getY()));
		return d;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double g = 6.67*1e-11;
		double f = g*this.myMass*b.getMass()/(calcDistance(b)*calcDistance(b));
		return f;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double dx = b.getX()-this.myXPos;
		double fx = calcForceExertedBy(b)*dx/calcDistance(b);
		return fx;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double dy = b.getY()-this.myYPos;
		double fy = calcForceExertedBy(b)*dy/calcDistance(b);
		return fy;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies){
			if(!b.equals(this)){
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies){
			if(!b.equals(this)){
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	/**
	 * This is a mutator method, modifies state of a celestial body
	 * (position and velocity)
	 * @param deltaT the time-step used in updating
	 * @param xforce the force in the x-direction
	 * @param yforce the force in the y-direction
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;

		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;

		double nx = myXPos + nvx*deltaT;
		double ny = myYPos + nvy*deltaT;

		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
