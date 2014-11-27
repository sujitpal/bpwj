package sjm.examples.sling;

import java.util.*;
import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.reserved.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A <code>ReservedLiteral</code> matches a specific string 
 * that a tokenizer returns as a reserved word type.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class ReservedLiteral extends Terminal {
/**
 * the literal to match
 */
	protected Token literal; 

/**
 * Constructs a reserved literal that will match the specified 
 * string as a reserved word.
 *
 * @param   string   the string to match as a token
 *
 * @return   a literal that will match the specified string
 */
public ReservedLiteral(String s) {
	literal = new Token(
		WordOrReservedState.TT_RESERVED, s, 0);
}
/**
 * Returns true if the literal this object equals a
 * assembly's next element.
 *
 * @param Object an element from an assembly
 *
 * @return true, if the specified literal equals the next 
 *         token from an assembly
 */
protected boolean qualifies(Object o) {
	return literal.equals((Token) o);
}
/**
 * Returns a textual description of this parser.
 *
 * @param   Vector   a list of parsers already printed in 
 *                   this description
 * 
 * @return a textual description of this parser
 */
public String unvisitedString(Vector visited) {
	return literal.toString();
}
}
