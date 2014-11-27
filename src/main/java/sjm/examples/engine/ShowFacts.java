package sjm.examples.engine;

import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows the construction of a couple of facts.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowFacts {
/**
 * Shows the construction of a couple of facts.
 */
public static void main(String[] args) {
	Fact d = new Fact(
		"city", 
		new Fact[]{
			new Fact("denver"),
			new Fact(new Integer(5280))});
	
	Fact j = new Fact(
		"city", "jacksonville", new Integer(8));
	
	System.out.println(d + "\n" + j);
}
}
