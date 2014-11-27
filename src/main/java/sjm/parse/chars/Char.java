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
 * A Char matches a character from a character assembly.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class Char extends Terminal {

/**
 * Returns true every time, since this class assumes it is 
 * working against a CharacterAssembly.
 *
 * @param   object   ignored
 *
 * @return   true, every time, since this class assumes it is 
 *           working against a CharacterAssembly
 */
public boolean qualifies(Object o) {
	return true;
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
	return "C";
}
}
