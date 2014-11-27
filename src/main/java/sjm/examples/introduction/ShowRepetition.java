package sjm.examples.introduction;

import java.util.*;
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
 * Show that a <code>Repetition</code> object creates 
 * multiple interpretations.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRepetition {
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	String s = "steaming hot coffee";
	Assembly a = new TokenAssembly(s);
	Parser p = new Repetition(new Word());

	Vector v = new Vector();
	v.addElement(a);

	System.out.println(p.match(v));	
}
}
