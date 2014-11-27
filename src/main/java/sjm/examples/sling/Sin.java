package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a sin function around another source
 * function. 
 * <p>
 * The sin function is the y component of a stone's location 
 * at an angle given in radians. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Sin extends SlingFunction {
/**
 * Constructs <code>sin(t)</code>.
 */
public Sin() {
	super(new T());
}
/**
 * Constructs a function object that wraps a sin function
 * around the given source function.
 */
public Sin(SlingFunction source) {
	super(source);
}
/** 
 * Returns, essentially, <code>sin(source.f(t))</code>. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, sin(source.f(t).y))</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, Math.sin(source[0].f(t).y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "sin(" + source[0] + ")";
}
}
