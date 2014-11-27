package sjm.examples.string;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a <code>trim</code> function around an 
 * instance of another <code>StringFunction</code> class. 
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Trim extends StringFunction {
/**
 * Construct a <code>Trim</code> function around the identity 
 * function, so that this object will just trim a supplied 
 * string.
 */
public Trim() {}
/**
 * Construct a <code>Trim</code> function that will
 * wrap itself around the supplied source.
 */
public Trim(StringFunction source) {
	super(source);
}
/**
 * Return a trimmed version of the value of <code>
 * source.f(s)</code>, where <code>s</code> is the supplied 
 * string, and <code>source</code> is this function's source 
 * function.
 */
public String f(String s) {
	return source.f(s).trim();
}
}
