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
 * This class provides a parser that recognizes regular 
 * expressions.
 * <p>
 * Regular expressions are a "metalanguage", which means they 
 * form a language for describing languages. For example, 
 * <code>a*</code> is a regular expression that describes a 
 * simple language whose elements are strings composed of 0 
 * or more <code>a's</code>. Thus the result of parsing 
 * <code>a*</code> is a new parser, namely a
 * parser that will match strings of <code>a's</code>.
 * <p>
 * This class exists to show how a simple regular expression 
 * parser works. It recognizes expressions according to 
 * the following rules.
 *
 * <blockquote><pre>
 *     expression    = term orTerm*;
 *     term          = factor nextFactor*;
 *     orTerm        = '|' term;
 *     factor        = phrase | phraseStar;
 *     nextFactor    = factor;
 *     phrase        = letterOrDigit | '(' expression ')';
 *     phraseStar    = phrase '*';
 *     letterOrDigit = Letter | Digit;
 * </pre></blockquote>
 *
 * These rules recognize conventional operator precedence. 
 * They also avoid the problem of left recursion, and their 
 * implementation avoids problems with the infinite loop 
 * inherent in the cyclic dependencies of the rules. 
 *  
 * @author Steven J. Metsker
 *
 * @version 1.0 								       
 */ 

public class RegularParser {
	protected Sequence expression;

/**
 * Returns a parser that will recognize a regular
 * expression. (Identical to <code>start()</code>).
 * 
 * @return a parser that will recognize a regular
 *         expression
 */
public Parser expression() {
	if (expression == null) {

		// expression = term orTerm*;
		expression = new Sequence();
		expression.add(term());
		expression.add(new Repetition(orTerm()));
	}
	return expression;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    factor = phrase | phraseStar; 
 */
protected Parser factor() {
	Alternation a = new Alternation();
	a.add(phrase());
	a.add(phraseStar());
	return a;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    letterOrDigit = Letter | Digit;
 *
 * This parser has an assembler that will pop a 
 * character and push a SpecificChar parser in its 
 * place.
 */
protected Parser letterOrDigit() {
	Alternation a = new Alternation();
	a.add(new Letter());
	a.add(new Digit());
	a.setAssembler(new CharAssembler());
	return a;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    nextFactor = factor;
 *
 * This parser has an assembler that will pop two
 * parsers and push a Sequence of them. 
 */
protected Parser nextFactor() {
	Parser p = factor();
	p.setAssembler(new AndAssembler());
	return p;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    orTerm = '|' term;
 *
 * This parser has an assembler that will pop two
 * parsers and push an Alternation of them. 
 */
protected Parser orTerm() {
	Sequence s = new Sequence();
	s.add(new SpecificChar('|').discard());
	s.add(term());
	s.setAssembler(new OrAssembler());
	return s;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *     phrase = letterOrDigit | '(' expression ')';
 */
protected Parser phrase() {
	Alternation a = new Alternation();
	a.add(letterOrDigit());
	
	Sequence s = new Sequence();
	s.add(new SpecificChar('(').discard());
	s.add(expression());
	s.add(new SpecificChar(')').discard());
	
	a.add(s);
	return a;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    phraseStar = phrase '*'; 
 *
 * This parser has an assembler that will pop a
 * parser and push a Repetition of it.
 */
protected Parser phraseStar() {
	Sequence s = new Sequence();
	s.add(phrase());
	s.add(new SpecificChar('*').discard());
	s.setAssembler(new StarAssembler());
	return s;
}
/**
 * Returns a parser that will recognize a regular
 * expression. 
 * 
 * @return a parser that will recognize a regular
 *         expression
 */
public static Parser start() {
	return new RegularParser().expression();
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    term = factor nextFactor*; 
 */
protected Parser term() {
	Sequence term = new Sequence();
	term.add(factor());
	term.add(new Repetition(nextFactor()));
	return term;
}
/**
 * Return a parser that will match a <code>
 * CharacterAssembly</code>, according to the value of a 
 * regular expression given in a string.
 *
 * For example, given the string <code>a*</code>, this 
 * method will return a parser which will match any element
 * of the set <code>{"", "a", "aa", "aaa", ...}</code>.
 *
 * @return a parser that will match a <code>
 *         CharacterAssembly</code>, according to the value
 *         of a regular expression in the given string
 *
 * @param   String   the string to evaluate
 *
 * @exception RegularExpressionException if this parser
 *            does not recognize the given string as a 
 *            valid expression
 */
public static Parser value(String s)
	throws RegularExpressionException {
		
	CharacterAssembly c = new CharacterAssembly(s);
	Assembly a = start().completeMatch(c);
	if (a == null) {
		throw new RegularExpressionException(
			"Improperly formed regular expression");
	}
	Parser p;
	try {
		p = (Parser) a.pop();
	} catch (Exception e) {
		throw new RegularExpressionException(
			"Internal error in RegularParser");
	}
	return p;
}
}
