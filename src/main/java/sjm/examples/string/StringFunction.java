package sjm.examples.string;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A StringFunction implements the method <code>f()</code> to
 * accept a string, apply a function to it, and return a 
 * string. This class is abstract, and each subclass
 * represents a different function to apply. The functions
 * wrap each other, to allow runtime composition of new
 * functions.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public abstract class StringFunction {
	protected StringFunction source;
/**
 * Construct a function whose source function is identity,
 * which does not change a string.
 */
public StringFunction() {
	this(new Identity());
}
/**
 * Construct a function whose source is the given function.
 */
public StringFunction(StringFunction source) {
	this.source = source;
}
/**
 * Each subclass should implement this method in manner
 * that applies a function to the input string. The class
 * name should represent the function applied here.
 */
public abstract String f(String s);
}
