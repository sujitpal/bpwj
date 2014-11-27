package sjm.parse.tokens;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A CaselessLiteral matches a specified String from an
 * assembly, disregarding case.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class CaselessLiteral extends Literal {
/**
 * Constructs a literal that will match the specified string,
 * given mellowness about case.
 *
 * @param   string   the string to match as a token
 *
 * @return   a literal that will match the specified string,
 *           disregarding case
 */
public CaselessLiteral(String literal) {
	super(literal);
}
/**
 * Returns true if the literal this object equals an
 * assembly's next element, disregarding case.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if the specified literal equals the next 
 *           token from an assembly, disregarding case
 */
protected boolean qualifies(Object o) {
	return literal.equalsIgnoreCase((Token) o);
}
}
