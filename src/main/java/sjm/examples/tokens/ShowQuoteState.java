package sjm.examples.tokens;

import java.io.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class demonstrates how <code>QuoteState</code> 
 * works.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowQuoteState {
/**
 * Demonstrate the operation of the quote state.
 */
public static void main(String args[]) throws IOException {
	Tokenizer t = new Tokenizer(
	    "Hamlet says #Alas, poor Yorick!# and " +
	    "#To be, or not...");
	
	t.setCharacterState('#', '#', t.quoteState());
	
	while (true) {
		Token tok = t.nextToken();
		if (tok.equals(Token.EOF)) {
			break;
		}
		System.out.println(tok);
	}
}
}
