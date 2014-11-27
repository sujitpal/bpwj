package sjm.examples.tests;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.robot.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Test the <code>RobotParser</code> class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowRobotTest {
/**
 * Test the <code>RobotParser</code> class.
 */
public static void main(String[] args) {
	new TokenTester(RobotParser.start()).test();
}
}
