package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class contains a Sling function and the number of
 * lines to use in rendering the function. Note that the
 * number of lines may itself be a function. Also note
 * that the number of lines to use when rendering will be
 * evaluated at the beginning of a plot, when time = 0.
 * At rendering time, the number of lines is:
 *
 * <blockquote><pre>
 *     nLine.eval().f(0).y
 * </pre></blockquote>
 *
 * For example, nLine might be 100*s1 + t. Say the slider is 
 * halfway over. At rendering time, t = 0 and nLine will 
 * evaluate to 100*.5 + 0, or 50. The function f(0) returns a 
 * point, which will be (0, 50). The y value of this point
 * is 50 and thus the number of lines to use will be 50.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Renderable {
	protected SlingFunction function;
	protected SlingFunction nLine;
/**
 * Create a function that will be rendered with the given
 * number of lines.
 *
 * @param   function   the sling function to render
 *
 * @param   nLine   the number of lines to use when
 *                  rendering; this may be any function
 */
public Renderable(
	SlingFunction function, SlingFunction nLine) {
		
	this.function = function;
	this.nLine = nLine;
}
/**
 * Find the upper and lower points the function will reach,
 * given the number of points used to plot it.
 *
 * @return a pairing of the upper and lower points the 
 *         function will reach, given the number of points 
 *         used to plot it
 */
public Extrema getExtrema() {
	return function.extrema(nLine() + 1);
}
/**
 * Return the number of lines to use to render this 
 * function.
 *
 * @return the number of lines to use to render this
 *         function.
 */
public int nLine() {
	int nl = (int) Math.round(nLine.eval().f(0).y);
	return Math.abs(nl);
}
}
