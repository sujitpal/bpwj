package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class represents time, which the Sling environment
 * defines to vary from 0 to 1 as a plot unfolds.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class T extends SlingFunction {
/**
 * Returns the given time
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return the given time, placing it in the y component,
 *         and augmenting a point with time also in the x
 *         component. The point returned is (t, t).
 */
public Point f(double t) {
	return new Point(t, t);
}
/**
 * Returns a string representation of the time function.
 */
public String toString() {
	return "t";
}
}
