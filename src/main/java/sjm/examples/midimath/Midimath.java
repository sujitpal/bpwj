package sjm.examples.midimath;

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
 * This class creates and uses a parser that recognizes
 * arithmetic expressions that use addition and 
 * multiplication. The rules of the Midimath language are:
 * 
 * <blockquote><pre>	
 *     expression = term ('+' term)*;
 *     term       = Num ('*' Num)*;
 * </pre></blockquote>
 *
 * This class exists to show operator precedence.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Midimath {
/**
 * Returns a parser that will recognize a Midimath
 * expression.
 * 
 * @return   a parser that will recognize a Midimath 
 *           expression
 */
public Parser expression() {
	Sequence expression = new Sequence();
	
	Sequence plusTerm = new Sequence();
	plusTerm.add(new Symbol('+').discard());
	plusTerm.add(term());
	plusTerm.setAssembler(new PlusAssembler());
	
	expression.add(term());
	expression.add(new Repetition(plusTerm));
	return expression;
}
/**
 * Demonstrate a parser for Midimath.
 */
public static void main(String args[]) {
	Assembly out = new Midimath().expression().bestMatch(
		new TokenAssembly("2 + 3 * 7 + 19"));
	System.out.println(out.pop());
}
/**
 * Returns a parser that will recognize arithmetic
 * expressions containing just multiplication.
 * 
 * @return   a parser that will recognize arithmetic
 *           expressions containing just multiplication
 */
protected Parser term() { 
	Sequence term = new Sequence();

	Num n = new Num();
	n.setAssembler(new NumAssembler());

	Sequence timesNum = new Sequence();
	timesNum.add(new Symbol('*').discard());
	timesNum.add(n);
	timesNum.setAssembler(new TimesAssembler());

	term.add(n);
	term.add(new Repetition(timesNum));
	return term;
}
}
