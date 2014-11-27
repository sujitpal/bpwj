package sjm.parse.tokens;

import java.util.*;
import sjm.parse.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A Symbol matches a specific sequence, such as 
 * <code><</code>, or <code><=</code> that a tokenizer
 * returns as a symbol. 
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class Symbol extends Terminal {
/**
 * the literal to match
 */
	protected Token symbol; 

/**
 * Constructs a symbol that will match the specified char.
 *
 * @param   char   the character to match. The char must be 
 *                 one that the tokenizer will return as a 
 *                 symbol token. This typically includes most 
 *                 characters except letters and digits. 
 *
 * @return   a symbol that will match the specified char
 */
public Symbol(char c) {
	this(String.valueOf(c));
}
/**
 * Constructs a symbol that will match the specified sequence
 * of characters.
 *
 * @param   String   the characters to match. The characters
 *                   must be a sequence that the tokenizer will 
 *                   return as a symbol token, such as
 *                   <code><=</code>.
 *
 * @return   a Symbol that will match the specified sequence
 *           of characters
 */
public Symbol(String s) {
	symbol = new Token(Token.TT_SYMBOL, s, 0);
}
/**
 * Returns true if the symbol this object represents equals an
 * assembly's next element.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if the specified symbol equals the next 
 *           token from an assembly
 */
protected boolean qualifies(Object o) {
	return symbol.equals((Token) o);
}
/**
 * Returns a textual description of this parser.
 *
 * @param   vector   a list of parsers already printed in 
 *                   this description
 * 
 * @return   string   a textual description of this parser
 *
 * @see Parser#toString()
 */
public String unvisitedString(Vector visited) {
	return symbol.toString();
}
}
