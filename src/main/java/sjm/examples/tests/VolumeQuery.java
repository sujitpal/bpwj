package sjm.examples.tests;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class provides an ambiguous parser in its <code>
 * query</code> method, which serves to show that
 * the test classes can find ambiguity.
 * <p>
 * The grammar this class supports is:
 * <blockquote><pre> 
 *
 *     query  = (Word | volume)* '?';
 *     volume = "cups" | "gallon" | "liter";
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class VolumeQuery {
/*
 * Return a parser that recognizes the grammar:
 * 
 *     query = (Word | volume)* '?';
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
/*
 * Return a parser that recognizes the grammar:
 * 
 *     volume = "cups" | "gallon" | "liter";
 */
public static Parser volume() {
	Parser a = new Alternation()
		.add(new Literal("cups"))
		.add(new Literal("gallon"))
		.add(new Literal("liter"));
	return a;
}
}
