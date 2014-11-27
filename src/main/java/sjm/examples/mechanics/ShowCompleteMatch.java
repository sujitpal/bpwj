package sjm.examples.mechanics;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.arithmetic.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows that Parser.completeMatch() returns
 * a complete match, or null.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCompleteMatch {
/**
 * Show that </code>Parser.completeMatch()</code>
 * returns a complete match, or null.
 */
public static void main(String[] args) 
	throws ArithmeticException {

	Parser p = ArithmeticParser.start();

	TokenAssembly ta = 
		new TokenAssembly("3 * 4 + 5 and more");
	
	System.out.println(p.bestMatch(ta));
	System.out.println(p.completeMatch(ta));
}
}
