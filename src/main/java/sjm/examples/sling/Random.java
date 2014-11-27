package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Objects of this class return a random number between 0
 * and 1 when asked for their value.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Random extends SlingFunction {
/**
 * Generate a random number r between 0 and 1, and return
 * the point (t, r).
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, random())</code>
 */
public Point f(double t) {
	return new Point(t, Math.random());
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "random";
}
}
