package sjm.examples.design;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Objects of this class maintain a running average. Each
 * number that is added with the <code>add</code> method
 * increases the count by 1, and the total by the amount
 * added.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class RunningAverage
	implements sjm.utensil.PubliclyCloneable {
		
	protected double count = 0;
	protected double total = 0;
/**
 * Add a value to the running average, increasing the count
 * by 1 and the total by the given value.
 *
 * @param   double   the value to add into the running average
 */
public void add(double d) {
	count++;
	total += d;
}
/**
 * Return the average so far.
 *
 * @return the average so far
 */
public double average() {
	return total / count;
}
/**
 * Return a copy of this object.
 *
 * @return a copy of this object
 */
public Object clone() {
	try {
		return super.clone();
	} catch (CloneNotSupportedException e) {
		// this shouldn't happen, since we are Cloneable
		throw new InternalError();
	}
}
}
