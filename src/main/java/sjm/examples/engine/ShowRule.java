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
 * Show a rule in action.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRule {
/**
 * Show a rule in action.
 */
public static void main(String[] args) {
	Program p = ShowProgram.altitudes(); // from above
	
	Variable name = new Variable ("Name");
	Variable alt = new Variable("Alt");
	Atom fiveThou = new Atom(new Integer(5000));

	Rule r = new Rule(new Structure[]{
		new Structure("highCity", new Term[]{name}),
		new Structure("city", new Term[]{name, alt}),
		new Comparison(">", alt, fiveThou)});

	System.out.println(r);
	
	p.addAxiom(r);

	Query q = new Query(p, 
		new Structure("highCity", new Term[]{name}));

	while (q.canFindNextProof()) {
		System.out.println(q);
	}
}
}
