package sjm.examples.mechanics;

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
 * The <code>match()</code> method of this class prints the 
 * collection of assemblies it receives, and the new 
 * collection it creates.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class VerboseRepetition extends Repetition {
/**
 * Constructs a VerboseRepetition of the given parser. 
 *
 * @param   parser   the parser to repeat
 *
 * @return   a VerboseRepetiton that will match the given
 *           parser repeatedly in successive matches
 */
public VerboseRepetition(Parser p) {
	super(p);
}
/**
 * Constructs a VerboseRepetition of the given parser 
 * with the given name.
 * 
 * @param   parser   the parser to repeat
 *
 * @param   name   a name to be known by
 *
 * @return   a VerboseRepetiton that will match the given
 *           parser repeatedly in successive matches
 */
public VerboseRepetition(Parser p, String name) {
	super(p, name);
}
/**
 * Just a verbose version of <code>Repetition.match()
 * </code>.
 */
public Vector match(Vector in) {	
	System.out.println(" in: " + in);
	Vector out = super.match(in);
	System.out.println("out: " + out);
	return out;
}
}
