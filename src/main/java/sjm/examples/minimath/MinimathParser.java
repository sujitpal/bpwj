package sjm.examples.minimath;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.arithmetic.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Show a properly working utility class that provides an 
 * parser for "Minimath", using the grammar:
 * 
 * <blockquote><pre>	
 *     e = Num m*;
 *     m = '-' Num;
 * </pre></blockquote>
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MinimathParser {
	protected static Sequence e;
/**
 * Return a parser that will recognize a Minimath
 * expression.
 *
 * @return   a parser that will recognize a Minimath 
 *           expression
 */
public static Parser e() {

// lazy-initialize e to prevent cycling

	if (e == null) {
		e = new Sequence();
		e.add(n());
		e.add(new Repetition(m()));
	}
	return e;
}
/*
 * a parser for the rule: m = '-' Num;
 */
protected static Parser m() {
	Sequence s = new Sequence();
	s.add(new Symbol('-').discard());
	s.add(n());
	s.setAssembler(new MinusAssembler());
	return s;
}
/**
 * Just a little demo.
 */
public static void main(String[] args) {
	Assembly a = start()
		.completeMatch(
			new TokenAssembly("25 - 16 - 9"));
	System.out.println(a.pop());
}
/*
 * a parser to recognize a number. By default, Num
 * stacks a token. Here we use NumAssembler to replace
 * the token with its double value.
 */
protected static Parser n() {
	return new Num().setAssembler(new NumAssembler());
}
/**
 * Returns a parser that will recognize a Minimath
 * expression.
 * 
 * @return   a parser that will recognize a Minimath
 *           expression
 */
public static Parser start() {
	return e();
}
}
