package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a ceil function around another source
 * function. 
 * <p>
 * The ceil function applied to a number x returns the 
 * smallest integer that is equal to or greater than x. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Ceil extends SlingFunction {
/**
 * Constructs <code>ceil(t)</code>.
 */
public Ceil() {
	super(new T());
}
/**
 * Constructs a function object that wraps a ceil function
 * around the given source function.
 */
public Ceil(SlingFunction source) {
	super(source);
}
/** 
 * Returns, essentially, <code>ceil(source.f(t))</code>. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, ceil(source.f(t).y))</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, Math.ceil(source[0].f(t).y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "ceil(" + source[0] + ")";
}
}
