package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 */

/** 
 * This class holds onto two points, so that methods that
 * find a maximum and minimum value over some range can
 * return both points as a single <code>Extrema<object>.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Extrema {
	public final Point min;
	public final Point max;
/**
 * Creates a pair of points as a single value.
 */
public Extrema(Point min, Point max) {
	this.min = min;
	this.max = max;
}
/**
 * Return the ratio of the width of the extremes to the
 * height of the extremes.
 *
 * @return the ratio of the width of the extremes to the
 *         height of the extremes
 */
public double aspectRatio() {
	double numer = max.x - min.x;
	double denom = max.y - min.y;
	if (numer == 0 || denom == 0) {
		return 1;
	} else {
		return numer / denom;
	}
}
/**
 * 
 */
public String toString() {
	return "(" + min + ", " + max + ")";
}
}
