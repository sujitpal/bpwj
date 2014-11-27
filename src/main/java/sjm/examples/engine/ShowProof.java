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
 * Show a simple query proving itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowProof {
/**
 * Return a small database of shipping charges.
 */
public static Program charges() {

	Fact [] facts = new Fact[]{
		new Fact("charge", "athens", new Integer(23)),
		new Fact("charge", "sparta", new Integer(13)),
		new Fact("charge", "milos", new Integer(17))
	};

	return new Program(facts); 
}
/**
 * Return a small database of customers.
 */
public static Program customers() {

	Fact [] facts = new Fact[]{
		new Fact("customer", "Marathon Marble", "sparta"),
		new Fact("customer", "Acropolis Construction", 
			"athens"),
		new Fact("customer", "Agora Imports", "sparta")
	};
 
	return new Program(facts); 
}
/**
 * Show a simple query proving itself.
 */
 
public static void main(String[] args) {
	Program p = new Program();
	p.append(charges());
	p.append(customers());

	System.out.println("Program:");
	System.out.println(p);
	
	Variable city = new Variable("City");
	Variable fee  = new Variable("Fee");
	Variable name = new Variable("Name");
	
	Structure s1 = new Structure(
		"charge", new Term[] {city, fee});
	
	Structure s2 = new Structure(
		"customer", new Term[] {name, city});

	// charge(City, Fee), customer(Name, City)
	Query q = new Query(p, new Structure[] {s1, s2});

	System.out.println("\nQuery:");
	System.out.println(q);

	System.out.println("\nProofs:");	
	while (q.canFindNextProof()) {
		System.out.println("City: " + city);
		System.out.println("Fee:  " + fee);
		System.out.println("Name: " + name);
		System.out.println();
	}
}
}
