package sjm.examples.string;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class wraps a <code>substring</code> function around 
 * an instance of another <code>StringFunction</code> class. 
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Substring extends StringFunction {
	protected int from;
	protected int to;
	protected boolean rest = false;
/**
 * Construct a <code>Substring</code> function that will
 * wrap itself around the supplied source, returning the
 * portion of a string from the given index to the string's
 * end.
 */
public Substring(StringFunction source, int from) {
	this(source, from, 0); 
	this.rest = true;
}
/**
 * Construct a <code>Substring</code> function that will
 * wrap itself around the supplied source, returning the
 * portion of a string from the given <code>from</code> 
 * index to the given <code>to</code> index.
 */
public Substring(StringFunction source, int from, int to) {
	super(source);
	this.from = from;
	this.to = to;
}
/**
 * Return a substring of the value of <code>source.f(s)
 * </code>, where <code>s</code> is the supplied string, and 
 * <code>source</code> is this function's source function.
 */
public String f(String s) {
	if (rest) {
		return source.f(s).substring(from);
	} else {
		return source.f(s).substring(from, to);
	}
}
}
