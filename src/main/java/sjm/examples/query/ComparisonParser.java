package sjm.examples.query;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;
import sjm.examples.logic.ArithmeticAssembler;
import sjm.examples.logic.AtomAssembler;
import sjm.examples.arithmetic.ArithmeticExpressionException;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This utility class provides support to the Jaql 
 * parser, specifically for <code>expression()</code>
 * and <code>comparison()</code> subparsers.
 *
 * The grammar this class supports is:
 *
 * <blockquote><pre>	
 *     comparison = arg operator arg;
 *     arg        = expression | QuotedString;
 *     expression = term ('+' term | '-' term)*;
 *     term       = factor ('*' factor | '/' factor)*;
 *     factor     = '(' expression ')' | Num | variable;
 *     variable   = Word;
 *     operator   = "<" | ">" | "=" | "<=" | ">=" | "!=";
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ComparisonParser {
	protected Sequence expression;
	protected Speller speller;
/**
 * Construct a ComparisonParser that will consult the
 * given speller for the proper spelling of variable
 * names.
 */
public ComparisonParser(Speller speller) {
	this.speller = speller;
}
/**
 * Returns a parser that will recognize a comparison
 * argument.
 */
public Parser arg() {

	// arg = expression | QuotedString;

	Alternation a = new Alternation();
	a.add(expression());
	a.add(
		new QuotedString()
		.setAssembler(new AtomAssembler()));
	return a;
}
/**
 * Returns a parser that will recognize a comparison.
 */
public Parser comparison() {
	Sequence s = new Sequence("comparison");
	s.add(arg());
	s.add(operator());
	s.add(arg());
	s.setAssembler(new ComparisonAssembler());
	return s;
}
/*
 * Recognize '/' followed by a factor.
 */
protected Parser divideFactor() {
	Sequence s = new Sequence("divideFactor");
	s.add(new Symbol('/').discard());
	s.add(factor());
	s.setAssembler(new ArithmeticAssembler('/'));
	return s;
}
/**
 * Returns a parser that will recognize an arithmetic
 * expression.
 */
public Parser expression() {
	/*
	 * This use of a static variable avoids the infinite 
	 * recursion inherent in the language definition.
	 */
	if (expression == null) {
		
		// expression = term ('+' term | '-' term)*;
		expression = new Sequence("expression");
		expression.add(term());
		
		// second part
		Alternation a = new Alternation();
		a.add(plusTerm());
		a.add(minusTerm());
		expression.add(new Repetition(a));
	}
	return expression;
}
/*
 * Recognize an expression in parens, or a number, or a
 * variable.
 */
protected Parser factor() {
	// factor = '(' expression ')' | Num | variable;
	Alternation factor = new Alternation("factor");

	//  '(' expression ')'
	Sequence s = new Sequence();
	s.add(new Symbol('(').discard());
	s.add(expression());
	s.add(new Symbol(')').discard());
	factor.add(s);

	// Num | variable
	factor.add(new Num().setAssembler(new AtomAssembler()));
	factor.add(variable());
	
	return factor;
}
/*
 * Recognize '-' followed by a term.
 */
protected Parser minusTerm() {
	Sequence s = new Sequence("minusTerm");
	s.add(new Symbol('-').discard());
	s.add(term());
	s.setAssembler(new ArithmeticAssembler('-'));
	return s;
}
/*
 * Recognize an operator.
 */
protected Parser operator() {
	Alternation a = new Alternation("operator");
	a.add(new Symbol('<'));
	a.add(new Symbol('>'));
	a.add(new Symbol('='));
	a.add(new Symbol("<="));
	a.add(new Symbol(">="));
	a.add(new Symbol("!="));
	return a;
}
/*
 * Recognize '+' followed by a term.
 */
protected Parser plusTerm() {
	Sequence s = new Sequence("plusTerm");
	s.add(new Symbol('+').discard());
	s.add(term());
	s.setAssembler(new ArithmeticAssembler('+'));
	return s;
}
/*
 * Recognize a "term", per the language definition.
 */
protected Parser term() {
	// term = factor ('*' factor | '/' factor)*;
	Sequence term = new Sequence("term");

	// first part
	term.add(factor());

	// second part
	Alternation a = new Alternation();
	a.add(timesFactor());
	a.add(divideFactor());
	
	term.add(new Repetition(a));
	return term;
}
/*
 * Recognize '*' followed by a factor.
 */
protected Parser timesFactor() {
	Sequence s = new Sequence("timesFactor");
	s.add(new Symbol('*').discard());
	s.add(factor());
	s.setAssembler(new ArithmeticAssembler('*'));
	return s;
}
/*
 * Recognizes any word.
 */
protected Parser variable() {
	return new Word()
		.setAssembler(new VariableAssembler(speller));
}
}
