package sjm.examples.introduction;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how to recognize terminals in a string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowTerminal {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	String s = "steaming hot coffee";
	Assembly a = new TokenAssembly(s);
	Parser p = new Word();

	while (true) {
		a = p.bestMatch(a);
		if (a == null) {
			break;
		}
		System.out.println(a);
	}
	
}
}
