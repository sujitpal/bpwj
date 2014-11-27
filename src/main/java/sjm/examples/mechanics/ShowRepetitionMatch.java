package sjm.examples.mechanics;

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
 * This class shows that a repetition object creates more
 * than one match from a single assembly.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRepetitionMatch {
/**
 * This class shows that a repetition object creates more
 * than one match from a single assembly.
 */
public static void main(String[] args) {
	
	Parser p = new Repetition(new Word());
	Vector v = new Vector();
	String s = "How many cups are in a gallon?";
	v.addElement(new TokenAssembly(s));
	System.out.println(p.match(v));
}
}
