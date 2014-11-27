package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A scale represents the relationship of two temporal 
 * variables that vary linearly with each other.
 *
 * In Sling, the most common example of a scale is the 
 * relationship of user coordinates to panel coordinates.
 *
 * For example, in plotting a unit circle, a square that 
 * bounds the circle has its minimum point at (-1, -1), and 
 * its maximum at (1, 1). The minimum is at the lower left 
 * corner, where the panel coordinate might be (52.0, 262.0). 
 * The maximum is at the upper right, where the panel 
 * coordinate might be (314.0, 0.0).
 *
 * If <code>c</code> is a circle function in user coordinates, 
 * then the following definition will create <code>s</code> as 
 * a function that returns the panel coordinate as a function 
 * of time:
 *
 * <blockquote><pre> 
 *     Scale s = 
 *         new Scale(min, c, max, lowerLeft, upperRight); 
 * </pre></blockquote>
 *
 * The function <code>s</code> maps every point on the user's 
 * circle to a point on the panel.		
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Scale extends SlingFunction {
/**
 * Constructs a scale with the right number of arguments, which
 * is useful for a prototypical scale.
 */
public Scale() {
	this(new Point(0, 0), new Point(1, 1));
}
/**
 * Constructs <code>scale((0, 0), t, (1, 1), bFrom, bTo)
 * </code>.
 *
 * @param   bFrom   a SlingFunction (usually a Point) that
 *                  establishes the "from" bound for some
 *                  variable b, which this scale represents
 *
 * @param   bTo     a SlingFunction (also usually a Point)
 *                  that establishes the "to" bound for the
 *                  variable.
 */
public Scale(SlingFunction bFrom, SlingFunction bTo) {
	this(
		new Point(0, 0), 
		new T(), 
		new Point(1, 1), 
		bFrom, 
		bTo);
}
/**
 * Constructs scale(aFrom, a, aTo, bFrom, bTo). The returned 
 * scale varies from bFrom to bTo as "a" varies from aFrom to
 * aTo. The "from" and "to" functions are usually points, so 
 * that the returned scale maps between two sets of
 * coordinates.
 */
public Scale(
	SlingFunction aFrom, SlingFunction a, SlingFunction aTo, 
	SlingFunction bFrom, SlingFunction bTo) {
		
	this.source = 
		new SlingFunction[] {aFrom, a, aTo, bFrom, bTo};
}
/**
 * Given the source functions aFrom, a, aTo, bFrom and bTo,
 * this function varies from bFrom to bTo as "a" varies
 * from aFrom to aTo. 
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point b, that maps a.f(t) into the scale 
 *         for b
 */
public Point f(double t) {
	Point aFrom = source[0].f(t);
	Point a     = source[1].f(t);
	Point aTo   = source[2].f(t);
	Point bFrom = source[3].f(t);
	Point bTo   = source[4].f(t);

	double x = linear(aFrom.x, a.x, aTo.x, bFrom.x, bTo.x);
	double y = linear(aFrom.y, a.y, aTo.y, bFrom.y, bTo.y);
	
	return new Point(x, y);
}
/*
 * To keep two variables "a" and "b" in a linear relationship,
 * we maintain the equation:
 * 
 *   (a - aFrom)/(aTo - aFrom) = (b - bFrom)/(bTo - bFrom)
 *
 * Solving for b, we get:
 *
 *   b = (a - aFrom)/(aTo - aFrom)*(bTo - bFrom) + bFrom
 *
 * This method returns b, taking care to watch for division
 * by zero. That occurs when "a" does not really vary at
 * all. In that case, this method returns (somewhat 
 * arbitrarily) the average value for b.
 */
protected double linear(
	double aFrom, double a, double aTo, 
	double bFrom, double bTo) {
		
	double denom = aTo - aFrom;
	if (denom == 0) {
		return (bTo + bFrom) / 2;
	} else {
		return (a - aFrom) / denom * (bTo - bFrom) + bFrom;
	}
}
/**
 * Return a string representation of this function.
 */
public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("scale(");
	for (int i = 0; i < 4; i++) {
		buf.append(source[i]);
		buf.append(", ");
	}
	buf.append(source[4]);
	buf.append(')');
	return buf.toString();
}
}
