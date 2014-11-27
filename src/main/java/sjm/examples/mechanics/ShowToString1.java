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
 * Show how a simple composite prints itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowToString1 {
/**
 * Show how a simple composite prints itself.
 */
public static void main(String[] args) {
	
	Alternation adjective = new Alternation();
	adjective.add(new Literal("steaming"));
	adjective.add(new Literal("hot"));
	
	Sequence good = new Sequence();
	good.add(new Repetition(adjective));
	good.add(new Literal("coffee"));
	
	String s = "hot hot steaming hot coffee";
	Assembly a = new TokenAssembly(s);
	System.out.println(good.bestMatch(a));
	System.out.println(good);
}
}
