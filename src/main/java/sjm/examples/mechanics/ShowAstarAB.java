package sjm.examples.mechanics;

import java.util.*;
import sjm.parse.*;
import sjm.parse.chars.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows that the "right" answer for a repetition 
 * object is not always to match all that it can.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAstarAB {
/** 
 * This class shows that the "right" answer for a repetition 
 * object is not always to match all that it can.
 */
public static void main(String[] args) {
	
	Parser aStar = new Repetition(new SpecificChar('a')); 
	
	Parser ab = new Sequence()
		.add(new SpecificChar('a'))
		.add(new SpecificChar('b')); // ab
		
	Parser aStarAB = new Sequence()
		.add(aStar)
		.add(ab); // a*ab
		
	Vector v = new Vector();
	String s = "aaaab";
	v.addElement(new CharacterAssembly(s));
	
	System.out.println(aStar.match(v));
	System.out.println(ab.match(aStar.match(v)));
	System.out.println(aStarAB.match(v));
}
}
