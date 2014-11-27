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
 * This class shows that a <code>Sequence</code> match may
 * widen and then narrow the state of a match.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowSequencePinch {
/**
 * Show that a <code>Sequence</code> match may widen and then
 * narrow the state of a match.
 */
public static void main(String[] args) {
	
	Parser s = new VerboseSequence()
		.add(new Repetition(new Word()))
		.add(new Symbol('!'));
	
	Vector v = new Vector();
	v.addElement(new TokenAssembly("Hello world!"));
	s.match(v);
}
}
