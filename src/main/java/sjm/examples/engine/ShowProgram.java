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
 * Show the construction and use of a simple program.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowProgram {
/**
 * Return a small database of cities and their altitudes.
 */
public static Program altitudes() {
	Fact [] facts = new Fact[]{
		new Fact("city", "abilene",      new Integer(1718)),
		new Fact("city", "addis ababa",  new Integer(8000)),
		new Fact("city", "denver",       new Integer(5280)),
		new Fact("city", "flagstaff",    new Integer(6970)),
		new Fact("city", "jacksonville", new Integer(8)),
		new Fact("city", "leadville",    new Integer(10200)),
		new Fact("city", "madrid",       new Integer(1305)),
		new Fact("city", "richmond",     new Integer(19)),
		new Fact("city", "spokane",      new Integer(1909)),
		new Fact("city", "wichita",      new Integer(1305))
		};
	
	Program p = new Program();
	for (int i = 0; i < facts.length; i++) {
		p.addAxiom(facts[i]);
	}
	return p; 
 
}
/**
 * Show the construction and use of a simple program.
 */
public static void main(String[] args) { 
	Program p = altitudes();
	
	Variable name = new Variable("Name");
	Variable height = new Variable("Height");
	Structure s = new Structure(
		"city", new Term[]{name, height});
	Query q = new Query(p, s);
 
	while (q.canFindNextProof()) {
		System.out.println(
			name + " is about " +
			height + " feet above sea level.");
	}
 
}
}
