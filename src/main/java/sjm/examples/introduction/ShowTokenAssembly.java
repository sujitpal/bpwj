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
 * Show how a TokenAssembly divides up a string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowTokenAssembly {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	String s = "int i = 3;";
	TokenAssembly a = new TokenAssembly(s);
	while (a.hasMoreElements()) {
		System.out.println(a.nextElement());
	}
}
}
