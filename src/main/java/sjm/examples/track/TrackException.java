package sjm.examples.track;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Signals that a parser could not match text after
 * a specific point.
 */
public class TrackException extends RuntimeException {
	protected String after, expected, found;

/**
 * Constructs a <code>TrackException</code> with the 
 * specified reasons for the exception.
 *
 * @param   after   an indication of what text was parsed
 *
 * @param   expected   an indication of what kind of thing 
 *                     was expected, such as a ')' token        
 *
 * @param   found   the text the thrower actually found
 */
public TrackException(
	String after, String expected, String found) {
		
	super("After   : " + after +
		"\nExpected: " + expected +
		"\nFound   : " + found);
	this.after = after;
	this.found = found;
	this.expected = expected;
}
/**
 * Returns some indication of what text was interpretable.
 *
 * @return   some indication of what text was interpretable
 */
public String getAfter() {
	return after;
}
/**
 * Returns some indication of what kind of thing was 
 * expected, such as a ')' token.
 *
 * @return   some indication of what kind of thing was 
 *           expected, such as a ')' token
 */
public String getExpected() {
	return expected;
}
/**
 * Returns the text element the thrower actually found when 
 * it expected something else.
 *
 * @return   the text element the thrower actually found 
 *           when it expected something else
 */
public String getFound() {
	return found;
}
}
