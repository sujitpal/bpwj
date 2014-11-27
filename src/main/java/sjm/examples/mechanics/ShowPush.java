package sjm.examples.mechanics;

import java.util.*;
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
 * Show the value of not pushing the element a terminal 
 * matches.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowPush {
/**
 * Show the value of not pushing the element a terminal 
 * matches.
 */
public static void main(String[] args) {
	
	Parser  open = new Symbol('(').discard();
	Parser close = new Symbol(')').discard();
	Parser comma = new Symbol(',').discard();

	Num num = new Num();
	
	Parser coord = new Sequence()
		.add(open)
		.add(num).add(comma).add(num).add(comma).add(num)
		.add(close);

	Assembly out = coord.bestMatch(
		new TokenAssembly("(23.4, 34.5, 45.6)"));

	Stack s = out.getStack();
	while (!s.empty()) {
		System.out.println(s.pop());
	}
}
}
