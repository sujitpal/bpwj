package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a floor function around another source
 * function. 
 * <p>
 * The floor function applied to a number x returns the 
 * largest integer that is equal to or less than x. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Floor extends SlingFunction {
/**
 * Constructs <code>floor(t)</code>.
 */
public Floor() {
	super(new T());
}
/**
 * Constructs a function object that wraps a floor function
 * around the given source function.
 */
public Floor(SlingFunction source) {
	super(source);
}
/** 
 * Returns, essentially, <code>floor(source.f(t))</code>. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, floor(source.f(t).y))</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, Math.floor(source[0].f(t).y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "floor(" + source[0] + ")";
}
}
