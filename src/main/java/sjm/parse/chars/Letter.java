package sjm.parse.chars;

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
 * A Letter matches any letter from a character assembly.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class Letter extends Terminal {

/**
 * Returns true if an assembly's next element is a letter.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if an assembly's next element is a letter
 */
public boolean qualifies(Object o) {
	Character c = (Character) o;
	return Character.isLetter(c.charValue());
}
/**
 * Create a set with one random letter.
 */
public Vector randomExpansion(int maxDepth, int depth) {
	char c = (char) (26.0 * Math.random() + 'a');
	Vector v = new Vector();
	v.addElement(new String(new char[]{c}));
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
	return "L";
}
}
