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
 * A tokenizerState returns a token, given a reader, an 
 * initial character read from the reader, and a tokenizer 
 * that is conducting an overall tokenization of the reader. 
 * The tokenizer will typically have a character state table 
 * that decides which state to use, depending on an initial 
 * character. If a single character is insufficient, a state 
 * such as <code>SlashState</code> will read a second 
 * character, and may delegate to another state, such as 
 * <code>SlashStarState</code>. This prospect of delegation is 
 * the reason that the <code>nextToken()</code> method has a 
 * tokenizer argument. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public abstract class TokenizerState {
/**
 * Return a token that represents a logical piece of a reader.
 * 
 * @return  a token that represents a logical piece of the 
 *          reader
 *
 * @param   PushbackReader   a reader to read from
 *
 * @param   c   the character that a tokenizer used to 
 *              determine to use this state
 *
 * @param   Tokenizer   the tokenizer conducting the overall
 *                      tokenization of the reader
 *
 * @exception   IOException   if there is any problem reading
 */
public abstract Token nextToken(
	PushbackReader r, int c, Tokenizer t)
	throws IOException;
}
