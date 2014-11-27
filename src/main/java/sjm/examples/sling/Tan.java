package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a tan function around another source
 * function. 
 * <p>
 * The tan function is the ratio of a sling stone's y
 * component to its x component.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Tan extends SlingFunction {
/**
 * Constructs <code>tan(t)</code>.
 */
public Tan() {
	super(new T());
}
/**
 * Constructs a function object that wraps a tan function
 * around the given source function.
 */
public Tan(SlingFunction source) {
	super(source);
}
/** 
 * Returns, essentially, <code>tan(source.f(t))</code>. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, tan(source.f(t).y))</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, Math.tan(source[0].f(t).y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "tan(" + source[0] + ")";
}
}
