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
 * A quoteState returns a quoted string token from a reader. 
 * This state will collect characters until it sees a match
 * to the character that the tokenizer used to switch to 
 * this state. For example, if a tokenizer uses a double-
 * quote character to enter this state, then <code>
 * nextToken()</code> will search for another double-quote 
 * until it finds one or finds the end of the reader.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class QuoteState extends TokenizerState {
	protected char charbuf[] = new char[16];
/*
 * Fatten up charbuf as necessary.
 */
protected void checkBufLength(int i) {
	if (i >= charbuf.length) {
		char nb[] = new char[charbuf.length * 2];
		System.arraycopy(charbuf, 0, nb, 0, charbuf.length);
		charbuf = nb;
	}
}
/**
 * Return a quoted string token from a reader. This method 
 * will collect characters until it sees a match to the
 * character that the tokenizer used to switch to this 
 * state.
 *
 * @return a quoted string token from a reader
 */
public Token nextToken(
	PushbackReader r, int cin, Tokenizer t)
	throws IOException {
		
	int i = 0;
	charbuf[i++] = (char) cin;
	int c;
	do {
		c = r.read();
		if (c < 0) {
			c = cin;
		}
		checkBufLength(i);
		charbuf[i++] = (char) c;
	} while (c != cin);
	 
	String sval = String.copyValueOf(charbuf, 0, i);
	return new Token(Token.TT_QUOTED, sval, 0);
}
}
