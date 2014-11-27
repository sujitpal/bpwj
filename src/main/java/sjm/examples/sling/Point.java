package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Objects of this class store two numbers that effectively
 * determine a point in two-dimensional space.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Point extends SlingFunction {
	public double x;
	public double y;
/**
 * Create a point with the given coordinates.
 */
public Point(double x, double y) {
	this.x = x;
	this.y = y;
}
/**
 * Points are not really functions at all, but the <code>
 * Point</code> class subclasses <code>SlingFunction</code> so
 * that they may serve in compositions of other functions. 
 * Points have nothing to compute, so the receiver always 
 * returns itself.
 *
 * @param   t   ignored
 *
 * @return this point
 */
public Point f(double t) {
	return this;
}
/**
 * Returns a string representation of this point.
 */
public String toString() {
	return "(" + x + ", " + y + ")";
}
}
