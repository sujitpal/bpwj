package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class combines the y component of each of two source 
 * functions to form a new two-dimensional function. This 
 * allows functions which are normally one-dimensional to 
 * recombine into a two-dimensional function.
 *
 * For example, both <code>sin</code> and <code>cos</code> are 
 * normally one-dimensional functions, returning a single 
 * number for any given value. The classes <code>Sin</code> 
 * and <code>Cos</code> store their results in the y dimension 
 * of a function, augmenting any particular point with t as 
 * the x value. Objects of the <code>Cartesian</code> class 
 * ignore the x component of each source function. The y 
 * component of the first source function becomes the x 
 * component of the <code>Cartesian</code> function, and the y 
 * component of the second function becomes the y component of 
 * the <code>Cartesian</code> function. 
 * <p>
 * Consider the following program, which plots a circle:
 * 
 * <blockquote><pre>
 *     theta = 2*pi*t;
 *     x = cos(theta);
 *     y = sin(theta);
 *     plot cartesian(x, y);
 * </pre></blockquote>
 *
 * The design principles at play in this package augment the x 
 * and y functions above, so that they are effectively 
 * <code>cartesian(t, cos(theta))</code> and 
 * <code>cartesian(t, sin(theta))</code>. The program 
 * recombines the y components of these functions into a new 
 * <code>cartesian</code> with an x value of <code>cos(theta)
 * </code> and a y value of <code>sin(theta)</code>. 
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Cartesian extends SlingFunction {
/**
 * Constructs <code>cartesian(t, t)</code>.
 */
public Cartesian() {
	super(new T(), new T());
}
/**
 * Constructs a function object that combines the y components
 * of the two sources into the x and y values of a new
 * function.
 */
public Cartesian(SlingFunction sX, SlingFunction sY) {
	super(sX, sY);
}
/**
 * Combine the y components of the sources into a new point.
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(sx.f(t).y, sy.f(t).y)</code>
 */
public Point f(double t) {
	return new Point(source[0].f(t).y, source[1].f(t).y);
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return "cartesian(" + source[0] + ", " + source[1] + ")";
}
}
