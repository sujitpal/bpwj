package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show the composition of the function <code>sin(10*t)</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowFunction {
/**
 * Show the composition of the function <code>sin(10*t)
 * </code>.
 */
public static void main(String[] args) {
	SlingFunction f = new Sin(
		new Arithmetic('*', new Point(0, 10), new T()));
	System.out.println(f);
}
}
