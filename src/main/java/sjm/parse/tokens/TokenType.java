package sjm.parse.tokens;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Objects of this class represent a type of token, such
 * as "number" or "word".
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
 public class TokenType {
	 protected String name;
/**
 * Creates a token type of the given name.
 */
public TokenType(String name) {
	this.name = name;
}
}
