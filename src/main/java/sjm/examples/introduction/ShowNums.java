package sjm.examples.introduction;

import sjm.parse.tokens.*;
import sjm.utensil.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show what counts as a number.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowNums {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	String s = "12 12.34 .1234 1234e-2";
	TokenAssembly a = new TokenAssembly(s);
	while (a.hasMoreElements()) {
		System.out.println(a.nextElement());
	}
}
}
