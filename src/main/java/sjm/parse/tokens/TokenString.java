package sjm.parse.tokens;

import java.io.*;
import java.util.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A TokenString is like a String, but it is a series of 
 * Tokens rather than a series of chars. Once a TokenString is 
 * created, it is "immutable", meaning it cannot change. This 
 * lets you freely copy TokenStrings without worrying about 
 * their state. 
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class TokenString {
/**
 * the tokens in this tokenString
 */
	protected Token tokens[];

/**
 * Constructs a tokenString from the supplied tokens.
 *
 * @param   tokens   the tokens to use
 *
 * @return    a tokenString constructed from the supplied 
 *            tokens
 */
public TokenString(Token[] tokens) {
	this.tokens = tokens;
}
/**
 * Constructs a tokenString from the supplied string. 
 *
 * @param   string   the string to tokenize
 *
 * @return    a tokenString constructed from tokens read from 
 *            the supplied string
 */
public TokenString(String s) {
	this(new Tokenizer(s));
}
/**
 * Constructs a tokenString from the supplied reader and 
 * tokenizer. 
 * 
 * @param   Tokenizer   the tokenizer that will produces the 
 *                      tokens
 *
 * @return    a tokenString constructed from the tokenizer's 
 *            tokens
 */
public TokenString(Tokenizer t) {
	Vector v = new Vector();
	try {
		while (true) {
			Token tok = t.nextToken();
			if (tok.ttype() == Token.TT_EOF) {
				break;
			}
			v.addElement(tok);
		};
	} catch (IOException e) {
		throw new InternalError(
			"Problem tokenizing string: " + e);
	}
	tokens = new Token[v.size()];
	v.copyInto(tokens);
}
/**
 * Returns the number of tokens in this tokenString.
 *
 * @return   the number of tokens in this tokenString
 */
public int length() {
	return tokens.length;
}
/**
 * Returns the token at the specified index.
 *
 * @param    index   the index of the desired token
 * 
 * @return   token   the token at the specified index
 */
public Token tokenAt(int i) {
	return tokens[i];
}
/**
 * Returns a string representation of this tokenString. 
 *
 * @return   a string representation of this tokenString
 */
public String toString() {
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < tokens.length; i++) {
		if (i > 0) {
			buf.append(" ");
		}	
		buf.append(tokens[i]);
	}
	return buf.toString();
}
}
