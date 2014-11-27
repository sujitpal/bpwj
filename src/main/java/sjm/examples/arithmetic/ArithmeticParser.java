package sjm.examples.arithmetic;

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
 * This class provides a parser that recognizes 
 * arithmetic expressions. This class includes the method 
 * <code>value</code>, which is a "façade" that provides an 
 * example and makes the parser easy to use. For example,
 * 
 * <blockquote><pre>
 * 
 *     System.out.println(
 *         ArithmeticParser.value("(5 + 4) * 3 ^ 2 - 81"));
 * </pre></blockquote>
 *
 * This prints out <code>0.0</code>.
 * 
 * <p>
 * This class exists to show how a simple arithmetic 
 * parser works. It recognizes expressions according to 
 * the following rules:
 * 
 * <blockquote><pre>	
 *     expression    = term (plusTerm | minusTerm)*;
 *     term          = factor (timesFactor | divideFactor)*;
 *     plusTerm      = '+' term;
 *     minusTerm     = '-' term;
 *     factor        = phrase expFactor | phrase;
 *     timesFactor   = '*' factor;
 *     divideFactor  = '/' factor;
 *     expFactor     = '^' factor;
 *     phrase        = '(' expression ')' | Num;
 * </pre></blockquote>
 * 
 * These rules recognize conventional operator precedence and 
 * associativity. They also avoid the problem of left 
 * recursion, and their implementation avoids problems with 
 * the infinite loop inherent in the cyclic dependencies of 
 * the rules. In other words, the rules may look simple, but
 * their structure is subtle. 
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 * 
 */

public class ArithmeticParser {
	protected Sequence expression;
	protected Alternation factor;

/*
 * Returns a parser that for the grammar rule:
 *    
 *     divideFactor = '/' factor;
 *
 * This parser has an assembler that will pop two 
 * numbers from the stack and push their quotient.
 */
protected Parser divideFactor() {
	Sequence s = new Sequence();
	s.add(new Symbol('/').discard());
	s.add(factor());
	s.setAssembler(new DivideAssembler());
	return s;
}
/*
 * Returns a parser that for the grammar rule:
 *    
 *     expFactor = '^' factor;
 *
 * This parser has an assembler that will pop two 
 * numbers from the stack and push the result of
 * exponentiating the lower number to the upper one.
 */
protected Parser expFactor() {
	Sequence s = new Sequence();
	s.add(new Symbol('^').discard());
	s.add(factor());
	s.setAssembler(new ExpAssembler());
	return s;
}
/**
 * Returns a parser that will recognize an arithmetic
 * expression. (Identical to <code>start()</code>).
 * 
 * @return a parser that will recognize an arithmetic
 *         expression
 */
public Parser expression() {
	/*
	 * This use of a static variable avoids the infinite 
	 * recursion inherent in the grammar.
	 */
	if (expression == null) {
		
		// expression = term (plusTerm | minusTerm)*;
		expression = new Sequence("expression");
		expression.add(term());
		
		Alternation a = new Alternation();
		a.add(plusTerm());
		a.add(minusTerm());
		
		expression.add(new Repetition(a));
	}
	return expression;
}
/*
 * Returns a parser that for the grammar rule:
 *
 *     factor = phrase expFactor | phrase;
 */
protected Parser factor() {
	/*
	 * This use of a static variable avoids the infinite
	 * recursion inherent in the grammar; factor depends
	 * on expFactor, and expFactor depends on factor.
	 */
	if (factor == null) {
		factor = new Alternation("factor");

		Sequence s = new Sequence();
		s.add(phrase());
		s.add(expFactor());
		
		factor.add(s);
		factor.add(phrase());
	}
	return factor;
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
 *    phrase = '(' expression ')' | Num;
 *
 * This parser adds an assembler to Num, that will 
 * replace the top token in the stack with the token's
 * Double value.
 */
protected Parser phrase() {
	Alternation phrase = new Alternation("phrase");

	Sequence s = new Sequence();
	s.add(new Symbol('(').discard());
	s.add(expression());
	s.add(new Symbol(')').discard());
	phrase.add(s);

	phrase.add(new Num().setAssembler(new NumAssembler()));
	return phrase;
}
/*
 * Returns a parser that for the grammar rule:
 *    
 *     plusTerm = '+' term;
 *
 * This parser has an assembler that will pop two 
 * numbers from the stack and push their sum.
 */
protected Parser plusTerm() {
	Sequence s = new Sequence();
	s.add(new Symbol('+').discard());
	s.add(term());
	s.setAssembler(new PlusAssembler());
	return s;
}
/**
 * Returns a parser that will recognize an arithmetic
 * expression.
 * 
 * @return   a parser that will recognize an 
 *           arithmetic expression
 */
public static Parser start() {
	return new ArithmeticParser().expression();
}
/*
 * Returns a parser that for the grammar rule:
 *
 *    term = factor (timesFactor | divideFactor)*;
 */
protected Parser term() {
	Sequence s = new Sequence("term");
	s.add(factor());
	
	Alternation a = new Alternation();
	a.add(timesFactor());
	a.add(divideFactor());
	
	s.add(new Repetition(a));
	return s;
}
/*
 * Returns a parser that for the grammar rule:
 *    
 *     timesFactor = '*' factor;
 *
 * This parser has an assembler that will pop two 
 * numbers from the stack and push their product.
 */
protected Parser timesFactor() {
	Sequence s = new Sequence();
	s.add(new Symbol('*').discard());
	s.add(factor());
	s.setAssembler(new TimesAssembler());
	return s;
}
/**
 * Return the value of an arithmetic expression given in a
 * string. This method is a façade, which provides an 
 * example of how to use the parser.
 *
 * @return the value of an arithmetic expression given in a
 *         string
 *
 * @param String the string to evaluate.
 *
 * @exception ArithmeticExpressionException if this 
 *            parser does not recognize the given string
 *            as a valid expression
 */
public static double value(String s)
	throws ArithmeticExpressionException {
		
	TokenAssembly ta = new TokenAssembly(s);
	Assembly a = start().completeMatch(ta);
	if (a == null) {
		throw new ArithmeticExpressionException(
			"Improperly formed arithmetic expression");
	}
	Double d;
	try {
		d = (Double) a.pop();
	} catch (Exception e) {
		throw new ArithmeticExpressionException(
			"Internal error in ArithmeticParser");
	}
	return d.doubleValue();
}
}
