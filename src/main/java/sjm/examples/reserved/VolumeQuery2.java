package sjm.examples.reserved;

import java.io.*;
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
 * This class shows the use of a customized tokenizer, and
 * the use of a terminal that looks for the new token type.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class VolumeQuery2 {
/**
 * Return a parser that recognizes the grammar:
 * 
 *    query = (Word | volume)* '?';
 *
 * @return   a parser that recognizes queries containing
 *           volumes and random words.
 */
public static Parser query() {
		
	Parser a = new Alternation()
		.add(new Word())
		.add(volume());

	Parser s = new Sequence()
		.add(new Repetition(a))
		.add(new Symbol('?'));

	return s;
}
/**
 * Return a customized tokenizer that uses WordOrReservedState
 * in place of WordState.
 *
 * @return   a custom tokenizer that uses WordOrReservedState
 *           in place of WordState
 */
public static Tokenizer tokenizer() {

	Tokenizer t = new Tokenizer();

	WordOrReservedState wors = new WordOrReservedState();
	wors.addReservedWord("cups");
	wors.addReservedWord("gallon");
	wors.addReservedWord("liter");
	
	t.setCharacterState( 'a',   'z', wors);
	t.setCharacterState( 'A',   'Z', wors);
	t.setCharacterState(0xc0,  0xff, wors);

	return t;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    volume = "cups" | "gallon" | "liter";
 *
 * This parser stacks the recognized word as an
 * argument to "VOL()".
 */
protected static Parser volume() {

	Parser p = new ReservedWord();

	// an anonymous Assembler subclass notes volume matches
		
	p.setAssembler(
		new Assembler() {
			public void workOn(Assembly a) {
				Object o = a.pop();
				a.push("VOL(" + o + ")");
			}
		});

	return p; 
}
}
