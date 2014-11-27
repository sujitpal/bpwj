package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a cos function around another source
 * function. 
 * <p>
 * The cos function is the x component of a stone's location 
 * at an angle given in radians. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Cos extends SlingFunction {
/**
 * Constructs <code>cos(t)</code>.
 */
public Cos() {
	super(new T());
}
/**
 * Constructs a function object that wraps a cos function
 * around the given source function.
 */
public Cos(SlingFunction source) {
	super(source);
}
/** 
 * Returns, essentially, <code>cos(source.f(t))</code>. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, cos(source.f(t).y))</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, Math.cos(source[0].f(t).y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "cos(" + source[0] + ")";
}
}
