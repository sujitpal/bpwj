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
 * Show a two-structure query.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowTwoStructureQuery {
/**
 * Show a two-structure query.
 */
public static void main(String[] args) {
	Program p = ShowProgram.altitudes(); // from above
	
	Variable name = new Variable ("Name");
	Variable alt = new Variable("Alt");
	Atom fiveThou = new Atom(new Integer(5000));

	Query q = new Query(p, new Structure[]{
		new Structure("city", new Term[]{name, alt}),
		new Comparison(">", alt, fiveThou)});

	while (q.canFindNextProof()) {
		System.out.println(name + " " + alt);
	}
}
}
