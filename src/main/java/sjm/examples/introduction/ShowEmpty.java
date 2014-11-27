package sjm.examples.introduction;

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
 * Show how to put the <code>Empty</code> class to good use.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowEmpty {
/**
 * Show the value of the <code>Empty</code> parser, using a 
 * list parser.
 * 
 * A list, in this example, is a pair of brackets around some
 * contents. The contents may be empty, or may be an actual
 * list. An actual list is one or more words, separated by
 * commas. That is, an actual list is a word followed by
 * zero or more sequences of (comma, word).
 */
public static void main(String args[]) {
	
	Parser empty, commaTerm, actualList, contents, list;

	empty = new Empty();
		
	commaTerm = new Sequence()
		.add(new Symbol(',').discard())
		.add(new Word());
		
	actualList = new Sequence()
		.add(new Word())
		.add(new Repetition(commaTerm));
	
	contents = new Alternation()
		.add(empty)
		.add(actualList);

	list = new Sequence()
		.add(new Symbol('[').discard())
		.add(contents)
		.add(new Symbol(']').discard());

	String test[] = new String[]{ 
		"[die_bonder_2, oven_7, wire_bonder_3, mold_1]",
		"[]",
		"[mold_1]"};
	for (int i = 0; i < test.length; i++) {
		TokenAssembly a = new TokenAssembly(test[i]);
		System.out.println(
			list.completeMatch(a).getStack());
	}
}
}
