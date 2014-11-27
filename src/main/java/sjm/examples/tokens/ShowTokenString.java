package sjm.examples.tokens;

import sjm.parse.*;
import sjm.parse.tokens.*;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows a collaboration of objects from classes
 * <code>Tokenizer</code>, <code>TokenStringSource</code>, 
 * <code>TokenString</code>, <code>TokenAssembly</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class ShowTokenString {
/**
 * Show a collaboration of token-related objects.
 */
public static void main(String args[]) {

	// a parser that counts words
	
	Parser w = new Word().discard();
	w.setAssembler(new Assembler() {
		public void workOn(Assembly a) {
			if (a.stackIsEmpty()) {
				a.push(new Integer(1));
			} else {
				Integer i = (Integer) a.pop();
				a.push(new Integer(i.intValue() + 1));
			}
		}
	});

	// a repetition of the word counter
	
	Parser p = new Repetition(w);

	// consume token strings separated by semicolons
	
	String s = "I came; I saw; I left in peace;";
	Tokenizer t = new Tokenizer(s);
	TokenStringSource tss = new TokenStringSource(t, ";");

	// count the words in each token string
	
	while (tss.hasMoreTokenStrings()) {
		TokenString ts = tss.nextTokenString();
		TokenAssembly ta = new TokenAssembly(ts);
		Assembly a = p.completeMatch(ta);
		System.out.println(
			ts + " (" + a.pop() + " words)");
	}
}
}
