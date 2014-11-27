package sjm.examples.reserved;

import java.io.*;
import java.util.*;
import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Override WordState to return known reserved words as 
 * tokens of type TT_RESERVED.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class WordOrReservedState extends WordState {
	Vector reserved = new Vector();

	/**
	 * A constant indicating that a token is a reserved word.
	 */
	public static final TokenType TT_RESERVED = 
		new TokenType("reserved");
/**
 * Adds the specified string as a known reserved word. 
 *
 * @param   String   the word to add
 */
public void addReservedWord(String word) {
	reserved.addElement(word);
}
/**
 * Return all the known reserved words.
 *
 * @return   Vector  all the known reserved words
 */
public Vector getReservedWords() {
	return reserved;
}
/**
 * Return a reserved token or a word token from a reader.
 *
 * @return a reserved token or a word token from a reader
 */
public Token nextToken(PushbackReader r, int c, Tokenizer t)
	throws IOException {
		
	Token tok = super.nextToken(r, c, t);
	if (reserved.contains(tok.sval())) {
		return new Token(TT_RESERVED, tok.sval(), 0);
	}
	return tok;
}
}
