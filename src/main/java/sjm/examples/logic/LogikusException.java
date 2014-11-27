package sjm.examples.logic;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Signals a problem parsing the text of a Logikus
 * program or query.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class LogikusException 
	extends RuntimeException {
/**
 * Constructs a <code>LogikusException</code> with no detail 
 * message.
 */
public LogikusException() {
	super();
}
/**
 * Constructs a <code>LogikusException</code> with the 
 * specified detail message. 
 *
 * @param String the detail message.
 */
public LogikusException(String s) {
	super(s);
}
}
