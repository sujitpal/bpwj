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
 * Show that a parser that contains a cycle prints 
 * itself without looping.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCycle {
/**
 * Show that a parser that contains a cycle prints 
 * itself without looping.
 */
public static void main(String args[]) {

	// ticks = "tick" | "tick" ticks;
	
	Alternation ticks = new Alternation();
	Literal     tick  = new Literal("tick");
	
	ticks
		.add(tick)
		.add(new Sequence().add(tick).add(ticks));

	System.out.println(
		ticks.bestMatch(
			new TokenAssembly("tick tick tick tick")));

	System.out.println(ticks);
	
}
}
