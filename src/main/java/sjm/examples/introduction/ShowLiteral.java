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
 * Show a parser that recognizes an int declaration.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowLiteral {
/**
 * Just a little demo.
 */
public static void main(String args[]) {
	Sequence s = new Sequence();
	s.add(new Literal("int"));
	s.add(new Word());
	s.add(new Symbol('='));
	s.add(new Num());
	s.add(new Symbol(';'));

	Assembly a = s.completeMatch(
		new TokenAssembly("int i = 3;"));
	 
	System.out.println(a);
}
}
