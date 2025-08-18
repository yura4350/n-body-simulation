/**
 * @author Iurii Beliaev
 * 
 * Simulation program for the planet simulation
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));

        // Reading values at beginning of file to
		// find the radius, store in variable rad and return it
		int numBod = s.nextInt();
		double rad = s.nextDouble();	
		s.close();
		
		return rad;
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {

		Scanner s = new Scanner(new File(fname));
			
		// # of bodies, radius
		int numBodies = s.nextInt(); 
		double rad =  s.nextDouble();

		// Array that can store nb CelestialBodies
		CelestialBody[] bodies = new CelestialBody[numBodies];

		for(int k=0; k < numBodies; k++) {
			double xCoor = s.nextDouble();
			double yCoor = s.nextDouble();
			double xVel = s.nextDouble();
			double yVel = s.nextDouble();
			double mass = s.nextDouble();
			String fn = s.next();
			CelestialBody currB = new CelestialBody(xCoor, yCoor, xVel, yVel, mass, fn);
			bodies[k] = currB;
		}
		s.close();

		return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 39447000.0;
		double dt = 25000.0;

		String fname= "./data/atom.txt";

		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}

		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");

		Double[] xForces = new Double[bodies.length];
		Double[] yForces = new Double[bodies.length];

	    //       to hold forces on each body

		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: in loop, calculate netForcesX and netForcesY and store in
			//       arrays xforces and yforces for each object in bodies

			for(int k=0; k < bodies.length; k++) {
				xForces[k] = bodies[k].calcNetForceExertedByX(bodies);
				yForces[k] = bodies[k].calcNetForceExertedByY(bodies);

  			}

			// loop over all bodies and call update
			// with dt and corresponding xforces and yforces arrays

			for(int k=0; k < bodies.length; k++){
				bodies[k].update(dt, xForces[k], yForces[k]);
			}

			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// loop over all bodies and call draw on each one

			for(CelestialBody b : bodies){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
