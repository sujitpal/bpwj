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
 * The idea of a symbol is a character that stands on its 
 * own, such as an ampersand or a parenthesis. For example, 
 * when tokenizing the expression <code>(isReady)&
 * (isWilling) </code>, a typical tokenizer would return 7 
 * tokens, including one for each parenthesis and one for 
 * the ampersand. Thus a series of symbols such as 
 * <code>)&( </code> becomes three tokens, while a series 
 * of letters such as <code>isReady</code> becomes a single 
 * word token.
 * <p>
 * Multi-character symbols are an exception to the rule 
 * that a symbol is a standalone character.  For example, a 
 * tokenizer may want less-than-or-equals to tokenize as a 
 * single token. This class provides a method for 
 * establishing which multi-character symbols an object of 
 * this class should treat as single symbols. This allows, 
 * for example, <code>"cat <= dog"</code> to tokenize as 
 * three tokens, rather than splitting the less-than and 
 * equals symbols into separate tokens.
 * <p>
 * By default, this state recognizes the following multi-
 * character symbols: <code>!=, :-, <=, >=</code> 
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SymbolState extends TokenizerState {
	SymbolRootNode symbols = new SymbolRootNode();
/**
 * Constructs a symbol state with a default idea of what 
 * multi-character symbols to accept (as described in the 
 * class comment).
 *
 * @return   a state for recognizing a symbol
 */
public SymbolState() {
	add("!=");
	add(":-");
	add("<=");
	add(">=");
}
/**
 * Add a multi-character symbol.
 *
 * @param   String   the symbol to add, such as "=:="
 */
public void add(String s) {
	symbols.add(s);
}
/**
 * Return a symbol token from a reader.
 *
 * @return a symbol token from a reader
 */
public Token nextToken(
	PushbackReader r, int first, Tokenizer t) 
	throws IOException {

	String s = symbols.nextSymbol(r, first); 
	return new Token(Token.TT_SYMBOL, s, 0);
}
}
