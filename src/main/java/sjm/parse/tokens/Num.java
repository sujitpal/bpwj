package sjm.parse.tokens;

import java.util.*;
import sjm.parse.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A Num matches a number from a token assembly.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class Num extends Terminal {

/**
 * Returns true if an assembly's next element is a number.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if an assembly's next element is a number as
 *           recognized the tokenizer
 */
protected boolean qualifies(Object o) {
	Token t = (Token) o;
	return t.isNumber();
}
/**
 * Create a set with one random number (between 0 and 
 * 100).
 */
public Vector randomExpansion(int maxDepth, int depth) {
	double d = Math.floor(1000.0 * Math.random()) / 10;
	Vector v = new Vector();
	v.addElement(Double.toString(d));
	return v;
}
/**
 * Returns a textual description of this parser.
 *
 * @param   vector   a list of parsers already printed in 
 *                   this description
 * 
 * @return   string   a textual description of this parser
 *
 * @see Parser#toString()
 */
public String unvisitedString(Vector visited) {
	return "Num";
}
}
