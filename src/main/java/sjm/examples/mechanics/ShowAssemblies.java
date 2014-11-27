package sjm.examples.mechanics;

import sjm.parse.*;
import sjm.parse.chars.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows that a repetition of an object of the
 * <code>Terminal</code> base class will match an entire
 * assembly. This example brings out the fact that a
 * <code>TokenAssembly</code> returns tokens as elements,
 * and <code>CharacterAssembly</code> returns individual
 * characters as elements.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAssemblies {
/**
 * This class shows that a repetition of an object of the
 * <code>Terminal</code> base class will match an entire
 * assembly. 
 *
 * @param   args   ignored
 */
public static void main(String[] args) {
	
	Parser p = new Repetition(new Terminal());

	String s = "She's a 'smart cookie'!";
	System.out.println(p.bestMatch(new TokenAssembly(s)));
	System.out.println(p.bestMatch(new CharacterAssembly(s)));
}
}
