package sjm.examples.query;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Signals that a given string is not the name of a known
 * variable.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class UnrecognizedVariableException 
	extends RuntimeException {
/**
 * Constructs a <code>UnrecognizedVariableException</code> 
 * with no detail message.
 */
public UnrecognizedVariableException() {
	super();
}
/**
 * Constructs a <code>UnrecognizedVariableException</code>
 * with the specified detail message. 
 *
 * @param String the detail message.
 */
public UnrecognizedVariableException(String s) {
	super(s);
}
}
