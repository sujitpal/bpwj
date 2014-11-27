package sjm.examples.introduction;

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
 * Show how to recognize a quoted string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowQuotedString {
/**
 *  Show how to recognize a quoted string.
 */
public static void main(String[] args) {
	Parser p = new QuotedString();
	String id = "\"Clark Kent\"";
	System.out.println(p.bestMatch(new TokenAssembly(id)));
}
}
