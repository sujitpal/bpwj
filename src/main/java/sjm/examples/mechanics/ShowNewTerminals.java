package sjm.examples.mechanics;

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
 * Show the use of new subclasses of <code>Terminal</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowNewTerminals {
/**
 * Show the use of new subclasses of <code>Terminal</code>.
 */
public static void main(String[] args) {

	/*  term     = variable | known;
	 *  variable = UppercaseWord;
	 *  known    = LowercaseWord;
	 */

	Parser variable = new UppercaseWord();
	Parser known    = new LowercaseWord();

	Parser term = new Alternation()
		.add(variable)
		.add(known);

	// anonymous Assembler subclasses note element type
		
	variable.setAssembler(
		new Assembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("VAR(" + o + ")");
			}
		});
		
	known.setAssembler(
		new Assembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("KNOWN(" + o + ")");
			}
		});

	// term* matching against knowns and variables:
	
	System.out.println(
		new Repetition(term).bestMatch(
			new TokenAssembly(
				"member X republican democrat")));
}
}
