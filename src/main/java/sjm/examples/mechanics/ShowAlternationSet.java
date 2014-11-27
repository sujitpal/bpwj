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
 * This class shows that an alternation can, by itself, 
 * create a collection of possible matches.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAlternationSet {
/**
 * Show that an alternation can, by itself, create a 
 * collection of possible matches.
 */
public static void main(String args[]) throws Exception {

	// assignment = Word '=' assignment | Word;
	
	Alternation assignment = new Alternation();
	
	assignment
		.add(new Sequence()
			.add(new Word())
			.add(new Symbol('=').discard())
			.add(assignment));
	assignment
		.add(new Word());
	
	String s = "i = j = k = l = m";

	Vector v = new Vector();
	v.addElement(new TokenAssembly(s));

	System.out.println(assignment.match(v));
}
}
