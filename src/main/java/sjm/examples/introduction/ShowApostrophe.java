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
 * Show that apostrophes can be parts of words and can
 * contain quoted strings.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowApostrophe {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	String s = "Let's 'rock and roll'!";
	TokenAssembly a = new TokenAssembly(s);
	while (a.hasMoreElements()) {
		System.out.println(a.nextElement());
	}
}
}
