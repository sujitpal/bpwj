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
 * Show how to use an anonymous variable.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAnonymous {
/**
 * Show how to use an anonymous variable.
 */
public static void main(String[] args) {

	// marriage(001, balthasar, grimelda, 14560512, 14880711);
	Fact m1 = new Fact("marriage", new Object[] {
		new Integer(1), 
		"balthasar", 
		"grimelda", 
		new Integer(14560512), 
		new Integer(14880711)});

	// marriage(257, kevin, karla, 19790623, present);
	Fact m257 = new Fact("marriage", new Object[] {
		new Integer(257), 
		"kevin", 
		"karla", 
		new Integer(19790623), 
		"present"});
	
	Program p = new Program();
	p.addAxiom(m1);
	p.addAxiom(m257);

	// marriage(Id, Hub, _, _, _);

	Variable id  = new Variable("Id");
	Variable hub = new Variable("Hub");
	Anonymous a  = new Anonymous();
	
	Query q = new Query(p, new Structure(
		"marriage", new Term[] {id, hub, a, a, a}));

	// output
	System.out.println("Program: \n" + p + "\n");
	System.out.println("Query:   \n" + q + "\n");
	System.out.println("Results: \n");

	while (q.canFindNextProof()) {
		System.out.println(
			"Id: " + id + ", Husband: " + hub);
	}
}
}
