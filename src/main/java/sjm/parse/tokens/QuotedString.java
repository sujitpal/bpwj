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
 * A QuotedString matches a quoted string, like "this one" 
 * from a token assembly.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class QuotedString extends Terminal {
/**
 * Returns true if an assembly's next element is a quoted 
 * string.
 *
 * @param   object   an element from a assembly
 *
 * @return   true, if a assembly's next element is a quoted 
 *           string, like "chubby cherubim".
 */
protected boolean qualifies(Object o) {
	Token t = (Token) o;
	return t.isQuotedString();
}
/**
 * Create a set with one random quoted string (with 2 to
 * 6 characters).
 */
public Vector randomExpansion(int maxDepth, int depth) {
	int n = (int) (5.0 * Math.random());
	
	char[] letters = new char[n + 2];
	letters[0] = '"';
	letters[n + 1] = '"';
	
	for (int i = 0; i < n; i++) {
		int c = (int) (26.0 * Math.random()) + 'a';
		letters[i + 1] = (char) c;
	}
	
	Vector v = new Vector();
	v.addElement(new String(letters));
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
	return "QuotedString";
}
}
