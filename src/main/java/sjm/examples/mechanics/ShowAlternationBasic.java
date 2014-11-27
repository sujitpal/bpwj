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
 * This class shows the basics of using an alternation.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAlternationBasic {
/**
 * Just show the basics of alternation.
 */
public static void main(String args[]) {
	
	Alternation a = new Alternation();
	a.add(new Literal("steaming"));
	a.add(new Literal("hot"));
	
	Vector v = new Vector();
	v.addElement(
		new TokenAssembly("hot hot steaming hot coffee"));

	System.out.println(
		"a match: \n" + a.match(v));
	
	System.out.println(
		"a* match: \n" + new Repetition(a).match(v));
}
}
