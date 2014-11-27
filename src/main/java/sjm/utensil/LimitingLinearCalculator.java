package sjm.utensil;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A LimitingLinearCalculator is a LinearCalculator where the
 * data points given in the constructor limit the extrapoltation.
 *
 * The X value of a LimitingLinearCalculator will never be
 * below the minimum of xFrom and xTo, and will never be above
 * the maximum of these two.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class LimitingLinearCalculator extends LinearCalculator {
/**
 * Create a LimitingLinearCalculator from known points on two scales.
 *
 * @param double xFrom
 * @param double xTo
 * @param double yFrom
 * @param double yTo
 */
public LimitingLinearCalculator(double xFrom, double xTo, double yFrom, double yTo) {
	super(xFrom, xTo, yFrom, yTo);
}
/**
 * Return the value on the first scale, corresponding to the given
 * value on the second scale. Limit the X value to be between xFrom
 * and xTo.
 *
 * @return the value on the first scale, corresponding to the given
 *         value on the second scale
 */
public double calculateXforGivenY(double y) {
	if (y <= yTo && y <= yFrom) {
		return yFrom <= yTo ? xFrom : xTo;
	}
	if (y >= yTo && y >= yFrom) {
		return yFrom >= yTo ? xFrom : xTo;
	}
	return super.calculateXforGivenY(y);
}
/**
 * Return the value on the second scale, corresponding to the given
 * value on the first scale. Limit the Y value to be between yFrom
 * and yTo.
 *
 * @return the value on the second scale, corresponding to the given
 *         value on the first scale
 */
public double calculateYforGivenX(double x) {
	if (x <= xTo && x <= xFrom) {
		return xFrom <= xTo ? yFrom : yTo;
	}
	if (x >= xTo && x >= xFrom) {
		return xFrom >= xTo ? yFrom : yTo;
	}
	return super.calculateYforGivenX(x);
}
}
