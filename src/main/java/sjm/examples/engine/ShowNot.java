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
 * Show a <code>Not</code> object in action.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowNot {
/**
 * Show a <code>Not</code> object in action.
 */
public static void main(String[] args) {
	
	Program p = new Program();

	// bachelor(X) :- male(X), not married(X);

	Variable x = new Variable("X");
	Structure s0 = new Structure("bachelor", new Term[]{x});
	Structure s1 = new Structure("male", new Term[]{x});
	Structure s2 = new Not("married", new Term[]{x});
	Rule r0 = new Rule(new Structure[]{s0, s1, s2});
	p.addAxiom(r0);

	// married(jim)

	p.addAxiom(new Fact("married", "jim"));

	// male(jeremy); male(jim);

	p.addAxiom(new Fact("male", "jeremy"));
	p.addAxiom(new Fact("male", "jim"));

	System.out.println(p);
	
	Variable b = new Variable("B");
	Query q = new Query(p, 
		new Structure("bachelor", new Term[]{b}));
 
	while (q.canFindNextProof()) {
		System.out.println();
		System.out.println(b + " is a bachelor");
	}
}
}
