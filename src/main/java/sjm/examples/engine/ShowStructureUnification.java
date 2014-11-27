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
 * Show two structures unifying.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowStructureUnification {
/**
 * Show two structures unifying.
 */
public static void main(String args[]) {

	// city(denver, 5280)
	Structure denver = new Structure("denver");
	Structure altitude = new Structure(new Integer(5280));
	Structure city = new Structure(
		"city", new Structure[]{denver, altitude});

	// city(Name, Altitude)
	Variable name = new Variable("Name");
	Variable alt = new Variable("Altitude");
	Structure vCity = new Structure(
		"city", 
		new Term[]{name, alt});

	// show the cities
	System.out.println(city);
	System.out.println(vCity);

	// unify, and show the variables
	vCity.unify(city);

	System.out.println("\n    After unifying: \n");
	
	System.out.println("Name = " + name);
	System.out.println("Alt  = " + alt);

}
}
