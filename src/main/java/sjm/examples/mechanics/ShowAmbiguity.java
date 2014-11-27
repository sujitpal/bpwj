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
 * This class shows than a parser can find more than
 * one way to completely consume an assembly.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAmbiguity {
/**
 * Show than a parser can find more than one way to 
 * completely consume an assembly.
 */
public static void main(String[] args) {

	// volume = "cups" | "gallon" | "liter";

	Parser volume = new Alternation()
		.add(new Literal("cups"))
		.add(new Literal("gallon"))
		.add(new Literal("liter"));

	// an anonymous Assembler subclass notes volume matches
		
	volume.setAssembler(
		new Assembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("VOL(" + o + ")");
			}
		});

	// query = (Word | volume)* '?';
		
	Parser wordOrVolume = new Alternation()
		.add(new Word())
		.add(volume);

	Parser query = new Sequence()
		.add(new Repetition(wordOrVolume))
		.add(new Symbol('?'));

	Vector v = new Vector();
	v.addElement(
		new TokenAssembly("How many cups are in a gallon?"));
	
	System.out.println(query.match(v));
}
}
