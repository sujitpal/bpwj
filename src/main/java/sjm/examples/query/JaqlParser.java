package sjm.examples.query;

import sjm.examples.track.*;
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
 * This class provides a parser that recognizes a 
 * select query, without any where clauses. "Jaql" stands 
 * for "Just Another Query Language".
 *
 * The grammar this class supports is:
 *
 * <blockquote><pre>	
 *     select        = "select" selectTerms "from" classNames
 *                     optionalWhere;
 *     selectTerms   = commaList(selectTerm);
 *     selectTerm    = expression;
 *     classNames    = commaList(className);
 *     className     = Word;
 *     optionalwhere = empty | "where" comparisons;
 *     comparisons   = commaList(comparison);
 *     commaList(p) = p (',' p)*;
 * </pre></blockquote>
 *
 * This grammar uses <code>expression</code> and <code>
 * comparison</code> from <code>ComparisonParser</code>.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class JaqlParser {
	protected Speller speller;
	protected static ComparisonParser comparisonParser;
/**
 * Construct a query language parser that will use
 * the given speller to find the proper spelling of class
 * and variable names.
 */
public JaqlParser(Speller speller) {
	this.speller = speller;
}
/*
 * Recognize a class name.
 */
protected Parser className() {
	return new Word().setAssembler(
		new ClassNameAssembler());
}
/*
 * Recognize a sequence of class names separated by commas.
 */
protected Parser classNames() {
	return commaList(className());
}
/*
 * Using the given parser, this method composes a new
 * parser with the grammar:
 * 
 *     commaList(p) = p (',' p)*;
 *
 * The Jaql language uses this construction several 
 * times.
 */
protected static Sequence commaList(Parser p) {
	Sequence commaP = new Track();
	commaP.add(new Symbol(',').discard());
	commaP.add(p);
	
	Sequence s = new Sequence();
	s.add(p);
	s.add(new Repetition(commaP));
	return s;
}
/*
 * Recognize a comparison -- just use <code>comparison
 * </code> from <code>ComparisonParser</code>.
 */
protected Parser comparison() {
	return comparisonParser().comparison();
}
/**
 * Return the parser to use for expression and 
 * comparison subparsers.
 */
public ComparisonParser comparisonParser() {
	if (comparisonParser == null) {
		comparisonParser = new ComparisonParser(speller);
	}
	return comparisonParser;
}
/*
 * Recognize a comma-separated sequence of comparisons.
 */
protected Parser comparisons() {
	return commaList(comparison());
}
/*
 * Recognize either nothing or a where clause.
 */
protected Parser optionalWhere() {
	Alternation a = new Alternation();
	a.add(new Empty());
	a.add(where());
	return a;
}
/**
 * Returns a parser that will recognize a select
 * statement.
 * 
 * @return a parser that will recognize a select
 *         statement.
 */
public Parser select() {
	Sequence s = new Track();
	s.add(new CaselessLiteral("select").discard());
	s.add(selectTerms());
	s.add(new CaselessLiteral("from").discard());
	s.add(classNames());
	s.add(optionalWhere());
	return s;
}
/*
 * Recognize a select term, which can be any valid
 * expression.
 */
protected Parser selectTerm() {
	// wrap expression so we can add an assembler
	Sequence s = new Sequence("selectTerm");
	s.add(comparisonParser().expression());
	s.setAssembler(new SelectTermAssembler());
	return s;
}
/*
 * Recognize a comma-separated sequence of select terms.
 */
protected Parser selectTerms() {
	return commaList(selectTerm());
}
/**
 * Returns a parser that will recognize a select
 * statement.
 * 
 * @return a parser that will recognize a select
 *         statement.
 */
public Parser start() {
	return select();
}
/*
 * Recognize a where clause, which is "where" followed by
 * a comma-separated list of comparisons.
 */
protected Parser where() {
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("where").discard());
	s.add(comparisons());
	return s;
}
}
