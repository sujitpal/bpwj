package sjm.parse.tokens;

import java.io.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A slashStar state ignores everything up to a closing star
 * and slash, and then returns the tokenizer's next token.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SlashStarState extends TokenizerState {
/**
 * Ignore everything up to a closing star and slash, and 
 * then return the tokenizer's next token.
 *
 * @return the tokenizer's next token
 */
public Token nextToken(
	PushbackReader r, int theStar, Tokenizer t) 
	throws IOException {
		
	int c = 0;
	int lastc = 0;
	while (c >= 0) {
		if ((lastc == '*') && (c == '/')) {
			break;
		}
		lastc = c;
		c = r.read();
	}
	return t.nextToken();
}
}
