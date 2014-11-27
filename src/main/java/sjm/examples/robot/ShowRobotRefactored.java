package sjm.examples.robot;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how to use a parser class that arranges its 
 * subparsers as methods.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRobotRefactored {
/** 
 * Show how to use a parser class that arranges its subparsers 
 * as methods.
 */
public static void main(String[] args) {
	Parser p = new RobotRefactored().command();
	String s = "place carrier at WB500_IN";
	System.out.println(p.bestMatch(new TokenAssembly(s)));
}
}
