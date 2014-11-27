package sjm.examples.regular;

import sjm.parse.*;
import sjm.parse.chars.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how to use the <code>RegularParser</code> class.
 *  
 * @author Steven J. Metsker
 *
 * @version 1.0 								       
 */

public class ShowRegularParser {
/**
 * Show some examples of matching regular expressions.
 */
public static void main(String args[])
	throws RegularExpressionException {

	// a*
	Parser aStar = RegularParser.value("a*");
	showMatch(aStar, "");
	showMatch(aStar, "a");
	showMatch(aStar, "aa");
	showMatch(aStar, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	// (a|b)*
	Parser abStar = RegularParser.value("(a|b)*");
	showMatch(abStar, "aabbaabaabba");
	showMatch(abStar, "aabbaabaabbaZ");

	// a few other examples
	showMatch(RegularParser.value("a*a*"), "aaaa");
	showMatch(RegularParser.value("a|bc"), "bc");
	showMatch(RegularParser.value("a|bc|d"), "bc");

	// four letters
	Parser L = new Letter();
	Parser L4 = new Sequence("LLLL").add(L).add(L).add(L).add(L);
	showMatch(L4, "java");
	showMatch(L4, "joe");
	showMatch(new Repetition(L), "coffee");
}
/*
 * Just a little help for main().
 */
private static void showMatch(Parser p, String s) {
	System.out.print(p);
	Assembly a = p.completeMatch(new CharacterAssembly(s));
	if (a != null) {
		System.out.print(" matches ");
	} else {
		System.out.print(" does not match ");
	}
	System.out.println(s);
}
}
