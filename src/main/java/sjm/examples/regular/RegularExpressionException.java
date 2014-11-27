package sjm.examples.regular;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Signals that a given string is not recognizable as a
 * regular expression.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class RegularExpressionException extends Exception {
/**
 * Constructs a <code>RegularExpressionException</code> 
 * with no detail message.
 */
public RegularExpressionException() {
	super();
}
/**
 * Constructs a <code>RegularExpressionException</code>
 * with the specified detail message. 
 *
 * @param String the detail message.
 */
public RegularExpressionException(String s) {
	super(s);
}
}
