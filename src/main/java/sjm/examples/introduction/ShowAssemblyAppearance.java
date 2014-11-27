package sjm.examples.introduction;

import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how an assembly prints itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAssemblyAppearance {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	
	String s1 = "Congress admitted Colorado in 1876.";
	System.out.println(new TokenAssembly(s1));
	
	String s2 = "admitted(colorado, 1876)";
	System.out.println(new TokenAssembly(s2));
}
}
