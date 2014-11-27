package sjm.examples.pretty;

import java.util.*;
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
 * Show how the pretty printer displays a deep match of
 * alternations. The grammar this class shows is:
 *
 * <blockquote><pre> 
 *     reptile     = crocodilian | squamata;
 *     crocodilian = crocodile | alligator;
 *     squamata    = snake | lizard;
 *     crocodile   = "nileCroc" | "cubanCroc";
 *     alligator   = "chineseGator" | "americanGator";
 *     snake       = "cobra" | "python";
 *     lizard      = "gecko" | "iguana";
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowPrettyAlternations {
/**
 * Returns a parser that recognizes some alligators. 
 */
public static Parser alligator() {
	Alternation a = new Alternation("<alligator>");
	a.add(new Literal("chineseGator"));
	a.add(new Literal("americanGator"));
	return a;
}
/**
 * Returns a parser that recognizes some crocs. 
 */
public static Parser crocodile() {
	Alternation a = new Alternation("<crocodile>");
	a.add(new Literal("nileCroc"));
	a.add(new Literal("cubanCroc"));
	return a;
}
/**
 * Returns a parser that recognizes members of the crocodilian
 * order. 
 */
public static Parser crocodilian() {
	Alternation a = new Alternation("<crocodilian>");
	a.add(crocodile());
	a.add(alligator());
	return a; 
}
/**
 * Returns a parser that recognizes some lizards. 
 */
public static Parser lizard() {
	Alternation a = new Alternation("<lizard>");
	a.add(new Literal("gecko"));
	a.add(new Literal("iguana"));
	return a;
}
/**
 * Show how a series of alternations appear when pretty-
 * printed.
 */
public static void main(String[] args) {
	PrettyParser p = new PrettyParser(reptile());
	p.setShowLabels(true);
	TokenAssembly ta = new TokenAssembly("gecko");
	Enumeration e = p.parseTrees(ta).elements();
	while (e.hasMoreElements()) {
		System.out.println("The input parses as:");
		System.out.println("---------------------------");
		System.out.println(e.nextElement());
	}
}
/**
 * Returns a parser that recognizes some reptiles.
 */
public static Parser reptile() {
	Alternation a = new Alternation("<reptile>");
	a.add(crocodilian());
	a.add(squamata());
	return a; 
}
/**
 * Returns a parser that recognizes some snakes. 
 */
public static Parser snake() {
	Alternation a = new Alternation("<snake>");
	a.add(new Literal("cobra"));
	a.add(new Literal("python"));
	return a;
}
/**
 * Returns a parser that recognizes some members of the 
 * squamata order. 
 */
public static Parser squamata() {
	Alternation a = new Alternation("<squamata>");
	a.add(snake());
	a.add(lizard());
	return a;
}
}
