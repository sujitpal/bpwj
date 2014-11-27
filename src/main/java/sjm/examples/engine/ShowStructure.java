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
 * This class shows a simple structure.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowStructure {
/**
 * Show a simple structure.
 */
public static void main(String args[]) {
	Structure denver = new Structure("denver");
	Structure altitude = new Structure(new Integer(5280));
	Structure city = new Structure(
		"city", new Structure[]{denver, altitude});
	System.out.println(city);
}
}
