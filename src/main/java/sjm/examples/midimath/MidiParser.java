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
 * This class creates and uses a parser that aims
 * to recognize simple arithmetic expressions, but gets
 * caught in a loop. Allowable expressions include the
 * use of multiplication, addition and parentheses. The
 * grammar for this language is:
 * 
 * <blockquote><pre>	
 *     expression = term ('+' term)*;
 *     term       = factor ('*' factor)*;
 *     factor     = '(' expression ')' | Num;
 * </pre></blockquote>
 *
 * This class implements this grammar as a utility class,
 * and avoids looping while building the subparsers.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MidiParser {
	protected static Sequence expression;
/**
 * Returns a parser that will recognize a Midimath
 * expression.
 * 
 * @return   a parser that will recognize a Midimath 
 *           expression
 */
public Parser expression() {
	if (expression == null) {
		expression = new Sequence();
		expression.add(term());
		expression.add(new Repetition(minusTerm()));
	}
	return expression;
}
/**
 * Demonstrate that this utility class does not loop.
 */
public static void main(String args[]) {
	Parser e = new MidiParser().expression();
	Assembly out = e.bestMatch(
		new TokenAssembly("111 - (11 - 1)"));
	System.out.println(out.pop());
}
/*
 * Returns a parser that for the grammar rule:
 *    
 *     minusTerm = '-' term;
 *
 * This parser has an assembler that will pop two 
 * numbers from the stack and push their difference.
 */
protected Parser minusTerm() {
	Sequence s = new Sequence();
	s.add(new Symbol('-').discard());
	s.add(term());
	s.setAssembler(new MinusAssembler());
	return s;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    term = '(' expression ')' | Num;
 *
 * This parser adds an assembler to Num, that will 
 * replace the top token in the stack with the token's
 * Double value.
 */
protected Parser term() {

	Sequence s = new Sequence();
	s.add(new Symbol('(').discard());
	s.add(expression());
	s.add(new Symbol(')').discard());
	
	Alternation a = new Alternation();
	a.add(s);
	a.add(new Num().setAssembler(new NumAssembler()));
	return a;
}
}
