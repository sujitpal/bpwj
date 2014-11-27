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
 * This class shows that a <code>Sequence</code> match is
 * equivalent to a series of <code>match()</code> calls.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowSequenceManual {
/**
 * Show that a <code>Sequence</code> match is equivalent to 
 * a series of <code>match()</code> calls.
 */
public static void main(String[] args) {
	
	Parser hello = new Literal("Hello");
	Parser world = new Literal("world");
	Parser bang  = new Symbol('!');

	Parser s = new Sequence()
		.add(hello)
		.add(world)
		.add(bang);
	
	Vector v = new Vector();
	v.addElement(new TokenAssembly("Hello world!"));
	
	System.out.println(
		bang.match(world.match(hello.match(v))));
	
	System.out.println(s.match(v));
}
}
