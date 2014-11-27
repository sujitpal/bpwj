package sjm.examples.tests;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.logic.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Test the <code>Logikus</code> parser class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowLogikusTester {
/**
 * Test the <code>LogikusParser</code> parser class.
 */
public static void main(String[] args) {
	new TokenTester(LogikusParser.start()).test();
}
}
