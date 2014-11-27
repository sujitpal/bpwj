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
 * This state will either delegate to a comment-handling 
 * state, or return a token with just a slash in it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SlashState extends TokenizerState {
	
	protected SlashStarState slashStarState = 
		new SlashStarState();
		
	protected SlashSlashState slashSlashState = 
		new SlashSlashState();
/**
 * Either delegate to a comment-handling state, or return a 
 * token with just a slash in it.
 *
 * @return   either just a slash token, or the results of 
 *           delegating to a comment-handling state
 */
public Token nextToken(
	PushbackReader r, int theSlash, Tokenizer t)
	throws IOException {
		
	int c = r.read();
	if (c == '*') {
		return slashStarState.nextToken(r, '*', t);
	}
	if (c == '/') {
		return slashSlashState.nextToken(r, '/', t);
	}
	if (c >= 0) {
		r.unread(c);
	}
	return new Token(Token.TT_SYMBOL, "/", 0);
}
}
