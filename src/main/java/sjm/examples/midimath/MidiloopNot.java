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
public class MidiloopNot {
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
	
		Sequence plusTerm = new Sequence();
		plusTerm.add(new Symbol('+').discard());
		plusTerm.add(term());
		plusTerm.setAssembler(new PlusAssembler());
	
		expression.add(term());
		expression.add(new Repetition(plusTerm));
	}
	return expression;
}
/**
 * Returns a parser that will recognize either
 * numbers, or arithmetic expressions in parentheses.
 *
 * @return   a parser that will recognize either
 *           numbers, or arithmetic expressions in 
 *           parentheses
 */
protected Parser factor() { 
	Alternation factor = new Alternation();

	Sequence parenExpression = new Sequence();
	parenExpression.add(new Symbol('(').discard());
	parenExpression.add(expression());
	parenExpression.add(new Symbol(')').discard());

	factor.add(parenExpression);
	factor.add(new Num().setAssembler(new NumAssembler()));
	return factor;
}
/**
 * Demonstrate that this utility class does not loop.
 */
public static void main(String args[]) {
	Parser e = new MidiloopNot().expression();
	Assembly out = e.bestMatch(
		new TokenAssembly("(7 + 13) * 5"));
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

	Sequence timesFactor = new Sequence();
	timesFactor.add(new Symbol('*').discard());
	timesFactor.add(factor());
	timesFactor.setAssembler(new TimesAssembler());

	term.add(factor());
	term.add(new Repetition(timesFactor));
	return term;
}
}
